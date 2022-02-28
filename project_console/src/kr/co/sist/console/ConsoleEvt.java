package kr.co.sist.console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import kr.co.sist.console.report.ReportEvt;
import kr.co.sist.console.view.ViewDialog;
import kr.co.sist.log.LogInEvt;

public class ConsoleEvt implements ActionListener {
	private Console con;

	public ConsoleEvt(Console con) {
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == con.viewBtn) {
			try { // ���ڸ� �Է����� �ʾ��� �� ���ܸ� �߻���Ų��
				new ViewDialog(con);
			} catch (NumberFormatException ne) { // ���� �������·� ����־����� Ȯ��
				JOptionPane.showMessageDialog(con, "���� �°� �Է��ߴ��� Ȯ�����ּ���");
			} catch (IndexOutOfBoundsException ie) { // �Ű����� �Է� ���� ���� ����ó��
				JOptionPane.showMessageDialog(con, "ù��° ��ĭ�� ū �� �� ��° ��ĭ�� ���� ���� �־��ּ���");
				// ��� ���̾�α׷� �ٲ� ��
			} // end catch
		} // end if

		if (e.getSource() == con.reportBtn) {
			if(LogInEvt.getId().equals("root") && LogInEvt.getPassword().equals("1111")) {
				//root �������� ���� �� �����Ѵ�.
				JOptionPane.showMessageDialog(con, "������ ������ �� �ִ� ������ ����");
			}else {
				// �� �ܿ� �α����� ������ ���������� ����
			try {
				new ReportEvt(con);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException ne) { // ���� �������·� ����־����� Ȯ��
				JOptionPane.showMessageDialog(con, "���� �°� �Է��ߴ��� Ȯ�����ּ���");
			} catch (IndexOutOfBoundsException ie) { // �Ű����� �Է� ���� ���� ����ó��
				JOptionPane.showMessageDialog(con, "ù��° ��ĭ�� ū �� �� ��° ��ĭ�� ���� ���� �־��ּ���");
				// ��� ���̾�α׷� �ٲ� ��
			} // end catch
			}// end else
		}// end if
	}// end actionPerformed

}
