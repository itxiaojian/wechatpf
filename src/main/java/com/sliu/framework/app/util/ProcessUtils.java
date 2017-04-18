package com.sliu.framework.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class ProcessUtils {
	public ProcessStatus execute(final long timeout, final String... command)
			throws IOException, InterruptedException, TimeoutException {

		ProcessBuilder pb = new ProcessBuilder(command);
		pb.redirectErrorStream(true);
		Process process = pb.start();

		Worker worker = new Worker(process);
		worker.start();
		ProcessStatus ps = worker.getProcessStatus();
		try {
			worker.join(timeout);
			if (ps.exitCode == ProcessStatus.CODE_STARTED) {
				// not finished
				worker.interrupt();
				throw new TimeoutException();
			} else {
				return ps;
			}
		} catch (InterruptedException e) {
			// canceled by other thread.
			worker.interrupt();
			throw e;
		} finally {
			process.destroy();
		}
	}

	private static class Worker extends Thread {
		private final Process process;
		private ProcessStatus ps;

		private Worker(Process process) {
			this.process = process;
			this.ps = new ProcessStatus();
		}

		public void run() {
			try {
				InputStream is = process.getInputStream();
				try {
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line = null;
					while ((line = br.readLine()) != null) {
						System.out.print(line);
					}
				} catch (IOException ignore) {
				}
				ps.exitCode = process.waitFor();

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		public ProcessStatus getProcessStatus() {
			return this.ps;
		}
	}

	public static class ProcessStatus {
		public static final int CODE_STARTED = -257;
		public volatile int exitCode;
		public volatile String output;
	}
}
