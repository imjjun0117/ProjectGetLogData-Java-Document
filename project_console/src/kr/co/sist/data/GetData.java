package kr.co.sist.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.co.sist.log.LogInEvt;

@SuppressWarnings("serial")
public class  GetData extends GetDataSuper {
	
	
	/**
	 * �ε����� ������ �����͸� ����Ʈ�� �����Ͽ� ��ȯ�Ѵ�
	 * 
	 * @param begin
	 * @param end
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> setList(String begin, String end) throws IOException {
		// super();
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader br = null;
		if(LogInEvt.getFile().exists()) {
		try {
			br = new BufferedReader(new FileReader(LogInEvt.getFile()));
			String temp = "";
			int first = 0;
			int last = 0;
			while ((temp = br.readLine()) != null) {
				first = temp.indexOf(begin);
				last = temp.indexOf(end, first);
				data.add(temp.substring(first + 1, last)); // ���� ������ �� list�� ����
			} // end while
		} finally {
			if (br != null) {
				br.close();
			} // end if
		} // end finally
		} // end if 
		return data;
	}// end setList

	public ArrayList<String> setList(String begin, String end, int start) throws IOException { // OverLoad
		// super();
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(LogInEvt.getFile()));
			String temp = "";
			int first = 0;
			int last = 0;
			while ((temp = br.readLine()) != null) {
				first = temp.indexOf(begin, start);
				last = temp.indexOf(end, first);
				data.add(temp.substring(first + 1, last)); // ���� ������ �� list�� ����
			} // end while
			return data;
		} finally {
			if (br != null) {
				br.close();
			} // end if
		} // end finally
	}// end setList

	/**
	 * 
	 * Ű ���� �������� �ľ� Overload
	 * @param begin
	 * @param end
	 * @param contains
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> setList(String begin, String end, String contains) throws IOException { // OverLoad
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(LogInEvt.getFile()));
			String temp = "";
			int first = 0;
			int last = 0;
			while ((temp = br.readLine()) != null) {
				if (temp.contains(contains)) {
					first = temp.indexOf(begin);
					last = temp.indexOf(end, first);
					data.add(temp.substring(first + 1, last)); // ���� ������ �� list�� ����
				} else {
					data.add("������");
				}
			} // end while
			return data;
		} finally {
			if (br != null) {
				br.close();
			} // end if
		} // end finally
	}// end setList
	
	/**
	 *	�ִ� ��� Ű�� ���� 
	 */
	public String printKey() throws IOException { 
		
		List<String> data = setList("=", "&", "key");
		Map<String, Integer> map = new HashMap<>();
		//�� �׸񺰷� �󸶳� ȣ��Ǿ�����
		for (int i = 0; i < data.size(); i++) {

			String key = data.get(i);
			Integer count = map.get(key); // value �Ҵ�
			int value = (count == null) ? 1 : ++count; // value�� null�̸� 1 �׷����ʴٸ� ++
			map.put(key, value); // map �Ҵ�

		} // end for

		return "�ִٻ�� Ű�� �̸��� Ƚ��\n"+getMaxKey(map)+" : "+map.get(getMaxKey(map))+"ȸ\n";
	}// getKey

	/**
	 * ������ �� ���� Ƚ�� ���ϱ�
	 * 
	 * @throws IOException
	 */
	public String printBrowser() throws IOException {

		List<String> browserList = setList("[", "]", 6);
		HashMap<String, Integer> count = new HashMap<String, Integer>();

		BufferedReader br = null;
		try {
			// �� �׸񺰷� �󸶳� ȣ��Ǿ����� ����
			for (int i = 0; i < browserList.size(); i++) {

				String key = browserList.get(i);
				Integer cnt = count.get(key); // value �Ҵ�
				int value = (cnt == null) ? 1 : ++cnt; // value�� null�̸� 1 �׷����ʴٸ� ++
				count.put(key, value); // map �Ҵ�

			} // end for

			// ���
			Iterator<String> keys = count.keySet().iterator();
			StringBuilder sb = new StringBuilder();
			sb.append("������ �� �����Ȳ\n");
			while (keys.hasNext()) {
				String key = keys.next();
				float all = ((float) count.get(key) / (float) browserList.size()) * 100; // �������ϱ�
				sb.append(key+" - "+count.get(key)+"("+String.format("%.2f", all)+"%)\n");
			} // end while
			return sb.toString();
		} // end if

		finally {
			if (br != null) {
				br.close();
			} // close
		} // end finally

	}// browser

	/**
	 * ���� ���� �����ϴ� �ð� ���ϱ�
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public String printTime() throws IOException {

		HashMap<String, Integer> tmCount = new HashMap<String, Integer>();
		ArrayList<String> data = setList(" ", ":");
		// �� �׸񺰷� �󸶳� ȣ��Ǿ����� ����
		for (int i = 0; i < data.size(); i++) {

			String key = data.get(i);
			Integer count = tmCount.get(key); // value �Ҵ�
			int value = (count == null) ? 1 : ++count; // value�� null�̸� 1 �׷����ʴٸ� ++
			tmCount.put(key, value); // map �Ҵ�

		} // end for
			// ���
		return "���� ���� �����ϴ� �ð� : "+getMaxKey(tmCount)+"\n";
	}// time

	/**
	 * ����, ���� Ƚ��(200, 404) ���ϱ�
	 * 
	 * @throws IOException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public String printSuccessFail() throws IOException {
		ArrayList<String> data = setList("[", "]");
		int success = 0;
		int fail = 0;
		int num = 0;
		for (int i = 0; i < data.size(); i++) {
			num = Integer.parseInt(data.get(i));
			// ���� ���� Ƚ�� ���ϱ�
			switch (num) {
			case 200:
				success++;
				break;
			case 404:
				fail++;
				break;
			}// end
		}

		// �� ���
		return "���� ����(200) Ƚ�� : "+success+"��\n���� ����(404) Ƚ�� : "+fail+"��\n";

	}// end sucessFail

	/**
	 * �������� ��û(403) Ƚ��, ���� ���ϱ�
	 * 
	 * @throws IOException
	 * @throws ArithmeticException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public String printAbnormalRequest() throws IOException, ArithmeticException {
		ArrayList<String> data = setList("[", "]");
		int abnormal = 0; // �������� ��ûȽ��
		int allRequest = data.size(); // ��� ��ûȽ��
		double proportion = 0;
		int num = 0;

		for (int i = 0; i < allRequest; i++) {
			num = Integer.parseInt(data.get(i));
			// �������� ��û Ƚ�� ���ϱ�
			switch (num) {
			case 403:
				abnormal++;
				break;
			}// end switch

		} // end for

		// ���� ���ϱ�
		proportion = Math.round((((double) abnormal / allRequest * 1.0) * 10000)) / 100.0;

		// ���
		return "���������� ��û(403)�� �߻� Ƚ�� : "+abnormal+"��, ���� : "+String.format("%.2f",proportion )+"%\n";
	}// end abnormalRequest

	/**
	 * ��û�� ���� ����(500) Ƚ��, ���� ���ϱ�
	 * 
	 * @throws IOException
	 * @throws ArithmeticException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public String printErrorRequest() throws IOException, ArithmeticException {
		ArrayList<String> data = setList("[", "]");
		int error = 0; // ������û Ƚ��
		int allRequest = data.size(); // ��ü ��û Ƚ��
		double proportion = 0; // ����

		int num = 0;
		for (int i = 0; i < allRequest; i++) {
			num = Integer.parseInt(data.get(i));
			// ������û Ƚ�� ���ϱ�
			switch (num) {
			case 500:
				error++;
				break;
			}// end switch

		} // end for
			// ������û ���� ���ϱ�
		proportion = Math.round((((double) error / allRequest * 1.0) * 10000)) / 100.0;

		// ���
		return "��û�� ���� ����(500)�� �߻� Ƚ�� : "+error+"��, ���� : "+String.format("%.2f", proportion)+"%\n";
		
	}

	/**
	 * �Է��ϴ� ���� ������ ���� ��� ���� ���� ������ �ִ� ��� Ű�� ����Ѵ�
	 */
	public String printKey(int firstLine, int lastLine) throws IOException {
		
		Map<String, Integer> map = new HashMap<>();
		ArrayList<String> data = setList("=", "&", "key");
		
		for (int i = firstLine - 1; i < lastLine; i++) {// �Ű������� ������ ���� ->�������� �Ȱ��� ���
			String key = data.get(i);
			Integer count = map.get(key);
			int value = (count == null) ? 1 : ++count;
			map.put(key, value);
		}
		// ��ȯ

		return "�ԷµǴ� ���ο� �ش��ϴ� ���� ���\n"+getMaxKey(map)+" : "+map.get(getMaxKey(map))+"ȸ\n";
	}// getKey


}//class
