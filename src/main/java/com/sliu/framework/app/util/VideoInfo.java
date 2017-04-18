package com.sliu.framework.app.util;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取视频的信息 FFMPEG homepage http://ffmpeg.org/about.html
 */
public class VideoInfo {
	// 视频路径
	private String ffmpegApp;
	// 视频时
	private int hours;
	// 视频分
	private int minutes;
	// 视频秒
	private float seconds;

	public VideoInfo() {
	}

	public VideoInfo(String ffmpegApp) {
		this.ffmpegApp = ffmpegApp;
	}

	public String toString() {
		return hours + ":" + minutes + ":" + seconds;
	}

	public void getVideoInfo(String videoFilename) throws IOException,
			InterruptedException {
		String tmpFile = videoFilename + ".tmp.png";
		ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y",
				"-i", videoFilename, "-vframes", "1", "-ss", "0:0:0", "-an",
				"-vcodec", "png", "-f", "rawvideo", "-s", "100*100", tmpFile);

		Process process = processBuilder.start();

		InputStream stderr = process.getErrorStream();
		InputStreamReader isr = new InputStreamReader(stderr);
		BufferedReader br = new BufferedReader(isr);
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		new File(tmpFile).delete();

		Pattern pattern = Pattern.compile("Duration: (.*?),");
		Matcher matcher = pattern.matcher(sb);

		if (matcher.find()) {
			String time = matcher.group(1);
			calcTime(time);
		}

		process.waitFor();
		if (br != null)
			br.close();
		if (isr != null)
			isr.close();
		if (stderr != null)
			stderr.close();
	}

	private void calcTime(String timeStr) {
		String[] parts = timeStr.split(":");
		hours = Integer.parseInt(parts[0]);
		minutes = Integer.parseInt(parts[1]);
		seconds = Float.parseFloat(parts[2]);
	}

	public String getFfmpegApp() {
		return ffmpegApp;
	}

	public void setFfmpegApp(String ffmpegApp) {
		this.ffmpegApp = ffmpegApp;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public float getSeconds() {
		return seconds;
	}

	public void setSeconds(float seconds) {
		this.seconds = seconds;
	}

	public static void main(String[] args) {
		VideoInfo videoInfo = new VideoInfo("D:\\ffmpeg\\ffmpeg.exe");
		try {
			videoInfo.getVideoInfo("d:/aa.flv");
			System.out.println(videoInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
