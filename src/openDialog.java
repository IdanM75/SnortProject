import java.awt.BorderLayout;  
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;


public class openDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public JRadioButton rdbtnRed;
	public JRadioButton rdbtnYellow;
	public JRadioButton rdbtnRandom;
	public JComboBox redWizdom;
	public JComboBox columnSize;
	public JComboBox rowSize;
	public JComboBox yellowWizdom;
	public JButton okButton;
	public JButton cancelButton;
	
	
	public static void main(String[] args) {
		try {
			openDialog dialog = new openDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public openDialog() {
		setBounds(450, 250, 340, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Idan Snort Project");
		
		
		rdbtnRed = new JRadioButton("Red");
		rdbtnRed.setSelected(true);
		rdbtnRed.setBounds(20, 32, 90, 23);
		contentPanel.add(rdbtnRed);
		
		rdbtnYellow = new JRadioButton("Yellow");
		rdbtnYellow.setBounds(20, 58, 90, 23);
		contentPanel.add(rdbtnYellow);
		
		rdbtnRandom = new JRadioButton("Random");
		rdbtnRandom.setBounds(20, 84, 90, 23);
		contentPanel.add(rdbtnRandom);
		
		
		ButtonGroup start = new ButtonGroup();
		start.add(rdbtnRed);
		start.add(rdbtnYellow);
		start.add(rdbtnRandom);
		
		
		redWizdom = new JComboBox();
		redWizdom.setModel(new DefaultComboBoxModel(new String[] {"Human ", "MinMax 1", "MinMax 3"}));
		redWizdom.setBounds(116, 33, 94, 20);
		contentPanel.add(redWizdom);
		
		columnSize = new JComboBox();
		columnSize.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "7", "8", "9", "10"}));
		columnSize.setSelectedIndex(5);
		columnSize.setBounds(220, 85, 94, 20);
		contentPanel.add(columnSize);
		
		rowSize = new JComboBox();
		rowSize.setModel(new DefaultComboBoxModel(new String[] {"5", "6", "7", "8", "9", "10"}));
		rowSize.setSelectedIndex(5);
		rowSize.setBounds(220, 33, 94, 20);
		contentPanel.add(rowSize);
		
		yellowWizdom = new JComboBox();
		yellowWizdom.setModel(new DefaultComboBoxModel(new String[] {"Human ", "MinMax 1", "MinMax 3"}));
		yellowWizdom.setBounds(116, 85, 94, 20);
		contentPanel.add(yellowWizdom);
		
		JLabel lblRedWizdom = new JLabel("Red Wisdom");
		lblRedWizdom.setBounds(116, 11, 94, 20);
		contentPanel.add(lblRedWizdom);
		
		JLabel lblYellowWizdom = new JLabel("Yellow Wisdom");
		lblYellowWizdom.setBounds(116, 64, 94, 20);
		contentPanel.add(lblYellowWizdom);
		
		JLabel lblRowSize = new JLabel("Row Size");
		lblRowSize.setBounds(220, 11, 90, 20);
		contentPanel.add(lblRowSize);
		
		JLabel lblColumnSize = new JLabel("Column Size");
		lblColumnSize.setBounds(220, 64, 90, 20);
		contentPanel.add(lblColumnSize);
		
		JLabel lblWhoStart = new JLabel("Who Start");
		lblWhoStart.setBounds(20, 11, 90, 20);
		contentPanel.add(lblWhoStart);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//okButton.setBounds(150, 201, 59, 30);
		//contentPanel.add(okButton);
	}
}
