package kr.co.sist.console.report;

import java.awt.FileDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import kr.co.sist.console.Console;
import kr.co.sist.data.GetData;

public class ReportEvt {
	Console con;

	/**
	 * ���ϴ��̾�α׸� ���� ���� ��ο� �̸��� ���� �� �����͸� �����մϴ�.
	 * 
	 * @param con
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public ReportEvt(Console con) throws IOException {
		this.con = con;

		FileDialog fd = new FileDialog(con, "������ �����մϴ�", FileDialog.SAVE);
		fd.setVisible(true);
		String directory = fd.getDirectory();
		String name = fd.getFile();
		File file = new File(directory + name);
		GetData gd = new GetData();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		if (con.jtfFirst.getText().equals("0") && con.jtfLast.getText().equals("0")) {// 0,0 �Է��̸� ��ü��
			bw.write(gd.run());
		} else {
			int firstLine = Integer.parseInt(con.jtfFirst.getText());
			int lastLine = Integer.parseInt(con.jtfLast.getText());
			if (firstLine > lastLine) {
				throw new IndexOutOfBoundsException();
			} // end if
			bw.write(gd.run(firstLine, lastLine));
		} // end else
		bw.flush();
		bw.close();
	}// ReportEvt
}// class
