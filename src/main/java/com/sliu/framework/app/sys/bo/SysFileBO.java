package com.sliu.framework.app.sys.bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.likegene.framework.core.BaseBO;
import com.likegene.framework.core.Result;
import com.likegene.framework.core.SettingUtil;
import com.likegene.framework.core.logger.Logger;
import com.likegene.framework.core.logger.LoggerFactory;
import com.sliu.framework.app.sys.controller.FileForm;
import com.sliu.framework.app.sys.model.SysFile;
import com.sliu.framework.app.util.AppUtil;
import com.sliu.framework.app.util.GenerateSequenceUtil;
import com.sliu.framework.app.util.OsCheck;
import com.sliu.framework.app.util.SSHHelper;
import com.sliu.framework.app.util.VideoInfo;
import com.sliu.framework.app.util.VideoThumbTaker;

/**
 * 文件管理 Business
 * 
 * @author lxt
 * @since 2014-11-10 14:25:23
 * 
 */
@Service
public class SysFileBO extends BaseBO<SysFile> {

	protected static Logger logger = LoggerFactory
			.getServiceLog(SysFileBO.class);

	@Override
	public <K extends Serializable> Result remove(K id) {
		SysFile f = get(id);
		if (f == null) {
			return null;
		}
		try {
			File file = new File(SettingUtil.getSetting("uploadfilesavepath",
					null).toString()
					+ f.getPath());
			if (file.exists()) {
				FileUtils.forceDelete(file);
			}

			String smallPath = f.getPath().replace(".", "_300x300.");
			String videoPath = f.getPath().replace(".flv", ".jpg");
			File smallFile = new File(SettingUtil.getSetting(
					"uploadfilesavepath", null).toString()
					+ smallPath);
			File videoFile = new File(SettingUtil.getSetting(
					"uploadfilesavepath", null).toString()
					+ videoPath);
			if (smallFile.exists()) {
				FileUtils.forceDelete(smallFile);
			}
			if (videoFile.exists()) {
				FileUtils.forceDelete(videoFile);
			}
			return super.remove(id);
		} catch (IOException e) {
			logger.error("removeFile1", "", e);
		}
		return null;
	}

	public String getIndex(String str, String flag) {
		String[] strs = str.split("\\[");
		String index = "";
		for (String st : strs) {
			if (st.indexOf(flag) > -1) {
				String jstr = st.substring(st.indexOf("ID(s)") + 5);
				String[] ss = jstr.split(",");
				if (ss.length > 0) {
					index = ss[0];
				}
				return index;
			}
		}
		return index;
	}

	public Result saveFiles(FileForm fileForm, HttpServletRequest request) {
		Result result = new Result();
		String contextRealPath = SettingUtil.getSetting("uploadfilesavepath",
				null).toString();
		List<SysFile> oaFiles = new ArrayList<SysFile>();
		String fileName = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		for (int i = 0; i < fileForm.getFileData().size(); i++) {
			MultipartFile fileData = fileForm.getFileData().get(i);
			SSHHelper sshHelper = new SSHHelper();
			try {
				if (fileData.getSize() > 0) {
					String uuid = GenerateSequenceUtil.generateSequenceNo();
					String extension = StringUtils.substringAfterLast(
							fileData.getOriginalFilename(), ".");
					String filePath = DateFormatUtils.format(new Date(),
							"yyyy/MM/dd") + "/" + uuid + "." + extension;
					String smallfilePath = DateFormatUtils.format(new Date(),
							"yyyy/MM/dd")
							+ "/"
							+ uuid
							+ "_300x300."
							+ extension;
					inputStream = fileData.getInputStream();
					fileName = contextRealPath + filePath;
					String smallFileName = contextRealPath + smallfilePath;
					if (fileData.getSize() > 500 * 1024 * 1024) {
						String msg = getMessage("OaFile.overMaxsize",
								new String[] { fileData.getOriginalFilename() });
						logger.warn("saveFiles", msg);
						result.addErrormsg(msg);
						continue;
					}
					java.io.File file = new java.io.File(fileName);
					file.getParentFile().mkdirs();
					outputStream = new FileOutputStream(file);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					String filesuffix = SettingUtil.getSetting("filesuffix",
							null).toString();
					if (filesuffix.contains(extension)) {
						// 生成缩略图
						Thumbnails.of(fileName).size(300, 300)
								.keepAspectRatio(false).toFile(smallFileName);
					}
					String duration = "";
					String sshUsername = SettingUtil
							.getSetting("ssh.username", null).toString().trim();
					String sshPassword = SettingUtil
							.getSetting("ssh.password", null).toString().trim();
					String sshIp = SettingUtil.getSetting("ssh.ip", null)
							.toString().trim();
					int sshPort = SettingUtil.getSetting("ssh.port",
							Integer.class);
					OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
					switch (ostype) {
					case Linux:
						boolean flag = sshHelper.loginServer(sshIp, sshPort,
								sshUsername, sshPassword);
						if (!flag) {
							logger.error("html2png", "登陆失败！ip:" + sshIp
									+ ",port:" + sshPort + ",username:"
									+ sshUsername + ",password:" + sshPassword);
						}
						break;
					case Other:
						break;
					}
					if ("flv".equals(extension)) {
						switch (ostype) {
						case Linux:
							// 视频截图
							String videoPath = fileName.replace(".flv", ".jpg");
							sshHelper
									.executeCmd("ffmpeg -i "
											+ fileName
											+ " -y -f image2 -ss 5 -t 0.001 -s 300x300 "
											+ videoPath);
							// 视频时长
							StringBuffer successStr = new StringBuffer();
							StringBuffer errorStr = new StringBuffer();
							sshHelper
									.executeCmd(
											"ffmpeg -i "
													+ fileName
													+ " 2>&1 | grep 'Duration' | cut -d '' -f 4 | sed s/,//",
											successStr, errorStr);
							String succStr = successStr.toString();
							String errStr = errorStr.toString();
							int indexA = succStr.indexOf("Duration:");
							int indexB = succStr.indexOf("start:");
							duration = succStr.substring(indexA + 9, indexB);
							duration = duration.trim();
							if (duration.indexOf(".") > -1) {
								duration = duration.substring(0,
										duration.indexOf("."));
							}
							break;
						case Windows:
							// 视频截图
							String path = request.getRealPath("/");
							VideoThumbTaker videoThumbTaker = new VideoThumbTaker(
									path + "resources\\ffmpeg\\ffmpeg.exe");
							String videoWindowPath = fileName.replace(".flv",
									".jpg");
							videoThumbTaker.getThumb(fileName, videoWindowPath,
									300, 300, 0, 0, 5);
							// 视频时长
							VideoInfo videoInfo = new VideoInfo(path
									+ "resources\\ffmpeg\\ffmpeg.exe");
							videoInfo.getVideoInfo(fileName);
							duration = videoInfo.toString();
							if (duration.indexOf(".") > -1) {
								duration = duration.substring(0,
										duration.indexOf("."));
							}
							break;
						}
					}
					String swftoolsearch = SettingUtil.getSetting(
							"swftoolsearch", null).toString();
					String swftooljpeg = SettingUtil.getSetting("swftooljpeg",
							null).toString();
					if ("swf".equals(extension)) {
						switch (ostype) {
						case Linux:
							// 视频截图
							String videoPath = fileName.replace(".swf", ".jpg");
							sshHelper.executeCmd("ffmpegthumbnailer -i "
									+ fileName + " -o " + videoPath
									+ " -s 300x300 -t 3");
							break;
						case Windows:
							String imgPath = fileName.replace(".swf", ".jpg");
							try {
								String cmd = String.format(swftoolsearch,
										fileName);
								Process p = Runtime.getRuntime().exec(cmd);
								BufferedReader bufferedReader = new BufferedReader(
										new InputStreamReader(
												p.getInputStream()));
								String str = "";
								String line = "";
								while ((line = bufferedReader.readLine()) != null) {
									str += line;
								}
								String j_index = this.getIndex(str, "-j]");
								String p_index = this.getIndex(str, "-p]");
								if (!"".equals(j_index)) {
									j_index = j_index.trim();
									cmd = String.format(swftooljpeg, "-j",
											j_index, fileName, imgPath);

								} else {
									cmd = String.format(swftooljpeg, "-p",
											p_index, fileName, imgPath);
								}
								Runtime.getRuntime().exec(cmd);
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						}
					}
					SysFile sysFile = new SysFile();
					sysFile.setId(uuid);
					if (StringUtils.isNotBlank(fileForm.getFolderId()))
						sysFile.setFolderId(fileForm.getFolderId());
					sysFile.setFileName(fileData.getOriginalFilename());
					sysFile.setUserId(AppUtil.getCurrentUser().getYhbh());
					sysFile.setUsername(AppUtil.getCurrentUser().getUsername());
					sysFile.setExtension(extension);
					sysFile.setType(fileData.getContentType());
					sysFile.setPath(filePath);
					sysFile.setSize(fileData.getSize());
					sysFile.setDescn(fileForm.getComments().get(i));
					sysFile.setCreateTime(new Date());
					sysFile.setRefId(fileForm.getRefId());
					sysFile.setRefObj(fileForm.getRefObj());
					sysFile.setDuration(duration);
					save(sysFile);
					oaFiles.add(sysFile);
				}
			} catch (Exception e) {
				logger.error("saveFiles", "", e);
			} finally {
				sshHelper.close();
				if (outputStream != null)
					try {
						outputStream.close();
					} catch (IOException e) {
						logger.error("saveFiles", "", e);
					}
				if (inputStream != null)
					try {
						inputStream.close();
					} catch (IOException e) {
						logger.error("saveFiles", "", e);
					}
			}
		}
		return result;
	}
}
