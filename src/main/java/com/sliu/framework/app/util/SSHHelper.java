package com.sliu.framework.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SSHHelper {
	/* 远程连接 */
	private Connection con = null;
	/* 缺省端口 */
	private final static int DEFAULT_PORT = 22;
	/* 错误信息 */
	private String errInfo = "";

	private final static long TIME_OUT = 0;

	private static Logger logger = Logger.getLogger(SSHHelper.class);

	/**
	 * 通过SSH登录服务器
	 * 
	 * @param ip
	 * @param userId
	 * @param pwd
	 * @return
	 */
	public boolean loginServer(String ip, int port, String userId, String pwd) {
		int serverPort = (port == 0 ? DEFAULT_PORT : port);
		con = new Connection(ip, serverPort);
		boolean isAuthenticated = false;
		try {
			con.connect();
			isAuthenticated = con.authenticateWithPassword(userId, pwd);
			logger.debug("登录服务器成功:servIP=" + ip + ", userId=" + userId);
		} catch (IOException e) {
			logger.error("登录服务器失败:servIP=" + ip + ", userId=" + userId + "\n"
					+ e);
			return false;
		}
		if (!isAuthenticated) {
			con.close();
		}
		return isAuthenticated;
	}

	public boolean executeCmd(String cmd, StringBuffer successLog,
			StringBuffer errorLog) throws IOException {

		Session session = null;
		session = con.openSession();
		session.execCommand(cmd);
		InputStream stdOut = new StreamGobbler(session.getStdout());
		InputStream stdErr = new StreamGobbler(session.getStderr());
		int condition = session.waitForCondition(ChannelCondition.EXIT_SIGNAL
				| ChannelCondition.EXIT_STATUS | ChannelCondition.CLOSED
				| ChannelCondition.EOF, TIME_OUT);
		String err = processStream(stdErr);

		errorLog.append(err);
		String info = processStream(stdOut);
		successLog.append(info);
		logger.debug("执行脚本输出信息(" + cmd + "):\n" + info);
		logger.debug("执行脚本异常信息(" + cmd + "):\n" + err);
		return true;
	}

	public boolean executeCmd(String cmd) throws IOException {

		Session session = null;
		session = con.openSession();
		session.execCommand(cmd);
		int condition = session.waitForCondition(ChannelCondition.EXIT_SIGNAL
				| ChannelCondition.EXIT_STATUS | ChannelCondition.CLOSED
				| ChannelCondition.EOF, TIME_OUT);
		return true;
	}

	/**
	 * 从输入流读取信息
	 * 
	 * @param in
	 * @return
	 */
	private String processStream(InputStream in) {
		StringBuilder sb = new StringBuilder("");
		try {
			byte[] buf = new byte[1024];
			while (in.read(buf) != -1) {
				int i = 0;
				while (i < buf.length && buf[i] != '\0') {
					i++;
				}
				sb.append(new String(buf, 0, i));
				buf = new byte[1024];
			}
		} catch (IOException e) {
			logger.error("读取信息异常\n" + e);
		}
		return sb.toString();
	}

	/**
	 * @return the errInfo
	 */
	public String getErrInfo() {
		return errInfo;
	}

	public void close() {
		if (con != null) {
			con.close();
		}
	}

	public static void main(String args[]) {
		SSHHelper ssh = new SSHHelper();
		boolean b = ssh
				.loginServer("192.168.119.144", 22, "ubuntu", "P@ssw0rd");
		StringBuffer successStr = new StringBuffer();
		StringBuffer errorStr = new StringBuffer();
		try {
			if (b) {
				ssh.executeCmd(
						"xvfb-run --server-args=\"-screen 0, 1024x768x24\" cutycapt --url=http://localhost:8080/resource/resourceUpload/resourcePropertyId/402881164b6c7360014b6c792c7a0002/id/402881164b6c7360014b6c792c760001 --out=/home/ubuntu/bmzg/share-files/temp/ResourceToImg/402881164b6c7360014b6c792c760001/content.jpg --max-wait=5000",
						successStr, errorStr);
				System.out.println(successStr.toString() + "," + errorStr);
			}
		} catch (Exception e) {
			ssh.close();
		}
	}
}
