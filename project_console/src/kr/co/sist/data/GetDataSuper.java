package kr.co.sist.data;

import java.io.IOException;
import java.util.Map;

abstract class GetDataSuper{
	public GetDataSuper() {
	}

	/**
	 * ���� ��������
	 * 
	 * @throws IOException
	 */
//	public DemoParent() {
//		FileDialog fd = new FileDialog(this, "����", FileDialog.LOAD);
//		fd.setVisible(true);
//		String path = fd.getDirectory();
//		String name = fd.getFile();
//
//		file = new File(path + name);
//		
//	}// file

	/**
	 * map�� �޾� �ִ�Ű�� ���ϴ� �޼ҵ�
	 * 
	 * @param map
	 * @return maxKey
	 */
	public static String getMaxKey(Map<String, Integer> map) {

		String maxKey = null;
		for (String key : map.keySet()) {
			if (maxKey == null || map.get(maxKey) < map.get(key)) {
				maxKey = key;// Ű���� �ִ� ���ϱ�
			} // end if
		} // end for
		return maxKey;

	}// end getMaxKey

	///////// �߻�޼ҵ� ����///////////////

	abstract public String printKey() throws IOException;

	abstract public String printBrowser() throws IOException;

	abstract public String printTime() throws IOException;

	abstract public String printSuccessFail() throws IOException;

	abstract public String printAbnormalRequest() throws IOException, ArithmeticException;

	abstract public String printErrorRequest() throws IOException, ArithmeticException;

	abstract public String printKey(int firstLine, int lastLine) throws IOException;

	//////// ���� �޼ҵ�/////////////
	/**
	 * �Է��ϴ� ���� �������
	 * 
	 * @return
	 */
	public String run() {
		StringBuilder sb = new StringBuilder();
		String line = "---------------------------------------\n";
		try {
			sb.append(printKey()).append(line).append(printBrowser()).append(line).append(printSuccessFail())
					.append(line).append(printTime());
			sb.append(line).append(printAbnormalRequest()).append(line).append(printErrorRequest()).append(line)
					.append(printKey());
		} catch (ArithmeticException ae) {
			ae.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch
		return sb.toString();
	}// run

	/**
	 * �Է��ϴ� ���� ���� ���
	 * 
	 * @param firstLine
	 * @param lastLine
	 */
	public String run(int firstLine, int lastLine) {
		StringBuilder sb = new StringBuilder();
		String line = "---------------------------------------\n";
		try {
			sb.append(printKey()).append(line).append(printBrowser()).append(line).append(printSuccessFail())
					.append(line).append(printTime());
			sb.append(line).append(printAbnormalRequest()).append(line).append(printErrorRequest()).append(line)
					.append(printKey(firstLine, lastLine));
		} catch (ArithmeticException ae) {
			ae.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch
		return sb.toString();
	}// run

}// class
