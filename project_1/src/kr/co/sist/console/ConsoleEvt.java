package kr.co.sist.console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ConsoleEvt implements ActionListener {
	private Console con;
	public ConsoleEvt(Console con) {
		this.con = con;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == con.viewBtn) {
			try { // ���ڸ� �Է����� �ʾ��� �� ���ܸ� �߻���Ų��
				new ViewDialog(con);
			}catch(NumberFormatException ne) { // ���� �������·� ����־����� Ȯ��
				JOptionPane.showMessageDialog(con,"���� �°� �Է��ߴ��� Ȯ�����ּ���");
			}catch (IndexOutOfBoundsException ie) { // �Ű����� �Է� ���� ���� ����ó��
				JOptionPane.showMessageDialog(con,"ù��° ��ĭ�� ū �� �� ��° ��ĭ�� ���� ���� �־��ּ���");
				//��� ���̾�α׷� �ٲ� ��
			} // end catch
		}//end if
	}//end actionPerformed

}
