package packageMain;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.Box;

public class MainPageQuizz extends JFrame {
	Random rand = new Random();
	public String[] vetor = new String[4];
	PreparedStatement stmt;
	ResultSet rs;
	ResultSet lenght;
	int az;
	String SQL, count;
	int lenghtMath, lenghtEnglish, lenghtProgramming;
	String explanation = null;
	private ConectionDB db = new ConectionDB();
	private JPanel contentPane;
	ProfileAdm studentAdm = new ProfileAdm();
	Font font1 = new Font("TimesRoman", Font.BOLD, 14);

	JLabel labelSwitch = new JLabel("Switch the quizz ");
	JButton mathButton = new JButton("Math");
	JButton programmingButton = new JButton("Programming");
	JButton englishButton = new JButton("English");
	JButton btnExit = new JButton("");
	JLabel chosenQuizz = new JLabel("Quizz");
	JButton continueQuestionsMath = new JButton("Next");
	JTextArea question = new JTextArea();
	JButton answer1 = new JButton("");
	JButton answer2 = new JButton("");
	JButton answer3 = new JButton("");
	JButton answer4 = new JButton("");

	JPanel panel3 = new JPanel();
	JPanel panelQuizz = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel1 = new JPanel();
	private final JButton buttonSeparatorProgramming = new JButton("");
	private final JButton buttonSeparatorEnglish = new JButton("");
	private final JButton buttonSeparatorMath = new JButton("");

	//public MainPageQuizz() {
	public void mainMethodQuizz() {
		questionsUser();
		MainQuizz();
		}

	public void questionsUser() {
		try {
			if (db.getConnection()) {
				String rsLenght = "SELECT MIN(codQuestion) AS firstRow FROM mathExercises;";
				stmt = db.con.prepareStatement(rsLenght);
				ResultSet lenght2 = stmt.executeQuery();

				while (lenght2.next()) {
					lenghtMath = lenght2.getInt("firstRow");
				}

				rsLenght = "SELECT MIN(codQuestion) AS firstRow FROM englishExercises;";
				stmt = db.con.prepareStatement(rsLenght);
				lenght2 = stmt.executeQuery();

				while (lenght2.next()) {
					lenghtEnglish = lenght2.getInt("firstRow");
				}

				rsLenght = "SELECT MIN(codQuestion) AS firstRow FROM programmingExercises;";
				stmt = db.con.prepareStatement(rsLenght);
				lenght2 = stmt.executeQuery();

				while (lenght2.next()) {
					lenghtProgramming = lenght2.getInt("firstRow");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MainQuizz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1137, 656);
		setVisible(true);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel1.setBackground(new Color(54, 33, 89));
		panel1.setBounds(0, 0, 360, 627);
		contentPane.add(panel1);
		panel1.setLayout(null);

		labelSwitch.setForeground(Color.WHITE);
		labelSwitch.setBackground(Color.WHITE);
		labelSwitch.setFont(new Font("Monospaced", Font.PLAIN, 16));
		labelSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		labelSwitch.setBounds(29, 77, 293, 56);
		panel1.add(labelSwitch);

		mathButton.setBackground(Color.LIGHT_GRAY);
		mathButton.setIcon(new ImageIcon(MainPageQuizz.class.getResource("/packageMain/icons8_math_25px.png")));
		mathButton.setFont(UIManager.getFont("TextArea.font"));
		mathButton.setBounds(10, 156, 339, 51);
		panel1.add(mathButton);

		programmingButton.setBackground(Color.LIGHT_GRAY);
		programmingButton.setIcon(new ImageIcon(MainPageQuizz.class.getResource("/packageMain/icons8_code_25px.png")));
		programmingButton
				.setSelectedIcon(new ImageIcon(MainPageQuizz.class.getResource("/packageMain/icons8_code_25px.png")));
		programmingButton.setFont(UIManager.getFont("TextArea.font"));
		programmingButton.setBounds(10, 218, 339, 50);
		panel1.add(programmingButton);

		englishButton.setBackground(Color.LIGHT_GRAY);
		englishButton.setIcon(
				new ImageIcon(MainPageQuizz.class.getResource("/packageMain/icons8_language_filled_25px.png")));
		englishButton.setFont(UIManager.getFont("TextArea.font"));
		englishButton.setBounds(10, 279, 339, 51);
		panel1.add(englishButton);

		btnExit.setIcon(new ImageIcon(MainPageQuizz.class.getResource("/packageMain/icons8_exit_sign_25px.png")));
		btnExit.setBackground(SystemColor.scrollbar);
		btnExit.setBounds(70, 567, 215, 37);
		panel1.add(btnExit);
		btnExit.setFont(UIManager.getFont("TextArea.font"));
		
		buttonSeparatorMath.setEnabled(false);
		buttonSeparatorMath.setBounds(345, 156, 15, 51);
		buttonSeparatorMath.setBackground(new Color(54, 33, 89));
		panel1.add(buttonSeparatorMath);
		
		buttonSeparatorMath.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
		buttonSeparatorProgramming.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
		buttonSeparatorEnglish.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));

		buttonSeparatorProgramming.setEnabled(false);
		buttonSeparatorProgramming.setBounds(345, 218, 15, 50);
		buttonSeparatorProgramming.setBackground(new Color(54, 33, 89));
		
		panel1.add(buttonSeparatorProgramming);
		buttonSeparatorEnglish.setEnabled(false);
		buttonSeparatorEnglish.setBounds(345, 279, 15, 51);
		buttonSeparatorEnglish.setBackground(new Color(54, 33, 89));
		
		panel1.add(buttonSeparatorEnglish);

		panel2.setBackground(new Color(176, 196, 222));
		panel2.setBounds(359, 0, 772, 627);
		contentPane.add(panel2);
		panel2.setLayout(null);

		panel3.setBounds(-13, 0, 796, 70);
		panel3.setBackground(new Color(54, 33, 89));
		panel2.add(panel3);
		panel3.setLayout(null);

		chosenQuizz.setHorizontalAlignment(SwingConstants.CENTER);
		chosenQuizz.setForeground(Color.WHITE);
		chosenQuizz.setFont(new Font("Monospaced", Font.PLAIN, 16));
		chosenQuizz.setBackground(Color.WHITE);
		chosenQuizz.setBounds(247, 11, 293, 56);
		panel3.add(chosenQuizz);

		panelQuizz.setBounds(-3, 70, 786, 557);
		panelQuizz.setBackground(new Color(176, 196, 222));
		panel2.add(panelQuizz);
		panelQuizz.setLayout(null);

		continueQuestionsMath.setIcon(
				new ImageIcon(MainPageQuizz.class.getResource("/packageMain/icons8_chevron_right_filled_25px.png")));
		continueQuestionsMath.setFont(UIManager.getFont("TextArea.font"));
		continueQuestionsMath.setBackground(SystemColor.scrollbar);
		continueQuestionsMath.setBounds(286, 509, 215, 37);
		panelQuizz.add(continueQuestionsMath);
		question.setText("");
		question.setBackground(UIManager.getColor("Button.darkShadow"));
//questionMath.setBackground(new Color(54, 33, 89));
		question.setRows(20);
		question.setLineWrap(true);
		question.setEnabled(false);
		question.setBounds(10, 12, 755, 266);
		question.setFont(font1);
		panelQuizz.add(question);

		answer1.setFont(UIManager.getFont("TextArea.font"));
		answer1.setBackground(SystemColor.scrollbar);
		answer1.setBounds(10, 296, 339, 51);
		panelQuizz.add(answer1);

		answer2.setFont(UIManager.getFont("TextArea.font"));
		answer2.setBackground(SystemColor.scrollbar);
		answer2.setBounds(10, 368, 339, 51);
		panelQuizz.add(answer2);

		answer3.setFont(UIManager.getFont("TextArea.font"));
		answer3.setBackground(SystemColor.scrollbar);
		answer3.setBounds(426, 296, 339, 51);
		panelQuizz.add(answer3);

		answer4.setFont(UIManager.getFont("TextArea.font"));
		answer4.setBackground(SystemColor.scrollbar);
		answer4.setBounds(426, 368, 339, 51);
		panelQuizz.add(answer4);

		mathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelQuizz.setVisible(true);
				chosenQuizz.setText("Math Quizz");
				buttonSeparatorMath.setBackground(new Color(176, 196, 222));
				mathButton.setBackground(new Color(176, 196, 222));
				buttonSeparatorMath.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(176, 196, 222), 1, true));
				mathButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(176, 196, 222), 1, true));
				
				buttonSeparatorProgramming.setBackground(new Color(54, 33, 89));
				buttonSeparatorProgramming.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				
				buttonSeparatorEnglish.setBackground(new Color(54, 33, 89));
				buttonSeparatorEnglish.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				
				programmingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				englishButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				
				englishButton.setBackground(Color.LIGHT_GRAY);
				programmingButton.setBackground(Color.LIGHT_GRAY);


				methodReceiverMath();
				actionsMath();
			}
		});

		englishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelQuizz.setVisible(true);
				chosenQuizz.setText("English Quizz");
				buttonSeparatorEnglish.setBackground(new Color(176, 196, 222));
				englishButton.setBackground(new Color(176, 196, 222));
				buttonSeparatorEnglish.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(176, 196, 222), 1, true));
				englishButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(176, 196, 222), 1, true));
				
				buttonSeparatorProgramming.setBackground(new Color(54, 33, 89));
				buttonSeparatorProgramming.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				
				buttonSeparatorMath.setBackground(new Color(54, 33, 89));
				buttonSeparatorMath.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				
				programmingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				mathButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				
				mathButton.setBackground(Color.LIGHT_GRAY);
				programmingButton.setBackground(Color.LIGHT_GRAY);
				methodReceiverEnglish();
				actionsEnglish();
			}
		});

		programmingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelQuizz.setVisible(true);
				chosenQuizz.setText("Programming Quizz");
				buttonSeparatorProgramming.setBackground(new Color(176, 196, 222));
				programmingButton.setBackground(new Color(176, 196, 222));
				buttonSeparatorProgramming.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(176, 196, 222), 1, true));
				programmingButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(176, 196, 222), 1, true));
				
				buttonSeparatorEnglish.setBackground(new Color(54, 33, 89));
				buttonSeparatorEnglish.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				
				buttonSeparatorMath.setBackground(new Color(54, 33, 89));
				buttonSeparatorMath.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				
				englishButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				mathButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(54, 33, 89), 1, true));
				
				mathButton.setBackground(Color.LIGHT_GRAY);
				englishButton.setBackground(Color.LIGHT_GRAY);
				
				methodReceiverProgramming();
				actionsProgramming();
			}
		});
	}

	public void methodReceiverMath() {
		if (db.getConnection()) {
			try {
				SQL = "SELECT question, answer0, answer1, answer2, answer3, explanation FROM mathExercises WHERE codQuestion="
						+ lenghtMath + ";";
				count = "SELECT COUNT(question) AS quantidade FROM mathExercises;";

				stmt = db.con.prepareStatement(count);
				lenght = stmt.executeQuery();

				while (lenght.next()) {
					if (lenghtMath > lenght.getInt("quantidade")) {
						JOptionPane.showMessageDialog(null,
								"You have finished all questions, please, try another course!");
					}
				}

				stmt = db.con.prepareStatement(SQL);
				rs = stmt.executeQuery();

				while (rs.next()) {

					vetor[0] = rs.getString("answer0");
					vetor[1] = rs.getString("answer1");
					vetor[2] = rs.getString("answer2");
					vetor[3] = rs.getString("answer3");
					int aleatorio = 10;
					int[] aux = new int[4];

					aleatorio = rand.nextInt(4);
					answer1.setText(String.valueOf(vetor[aleatorio]));
					aux[0] = aleatorio;

					while (aux[0] == aleatorio)
						aleatorio = rand.nextInt(4);
					answer2.setText(String.valueOf(vetor[aleatorio]));
					aux[1] = aleatorio;

					while (aux[0] == aleatorio || aux[1] == aleatorio)
						aleatorio = rand.nextInt(4);
					answer3.setText(String.valueOf(vetor[aleatorio]));
					aux[2] = aleatorio;

					while (aux[0] == aleatorio || aux[1] == aleatorio || aux[2] == aleatorio)
						aleatorio = rand.nextInt(4);
					answer4.setText(String.valueOf(vetor[aleatorio]));

					question.setText("Question " + lenghtMath + " - " + String.valueOf(rs.getString("question")));

					az++;

				}
			} catch (Exception error) {
				System.err.println("Error:" + error.getMessage());
				System.err.println("Error:" + error.toString());

			}

		}
	}

	public void actionsMath() {
		try {
			SQL = "SELECT question, answer0, answer1, answer2, answer3, explanation FROM mathExercises WHERE codQuestion="
					+ lenghtMath + ";";

			stmt = db.con.prepareStatement(SQL);
			lenght = stmt.executeQuery();
			while (lenght.next())
				explanation = String.valueOf(lenght.getString("explanation"));

			answer1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtMath++;
						if (answer1.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);
						}

						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			answer2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtMath++;
						if (answer2.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);

// objMath.explanationQuestion(i);
						}

						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			answer3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtMath++;
						if (answer3.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);

// objMath.explanationQuestion(i);
						}
						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			answer4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtMath++;
						if (answer4.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							JOptionPane.showMessageDialog(null, "You don't hit the correct answer!");
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);

// objMath.explanationQuestion(i);
						}
						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);

						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			continueQuestionsMath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					answer1.setEnabled(true);
					answer2.setEnabled(true);
					answer3.setEnabled(true);
					answer4.setEnabled(true);
					continueQuestionsMath.setEnabled(false);
					methodReceiverMath();
				}
			});

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

//==========================================================================

	public void methodReceiverEnglish() {
		if (db.getConnection()) {
			try {
				SQL = "SELECT question, answer0, answer1, answer2, answer3, explanation FROM englishExercises WHERE codQuestion="
						+ lenghtEnglish + ";";
				count = "SELECT COUNT(question) AS quantidade FROM englishExercises;";

				stmt = db.con.prepareStatement(count);
				lenght = stmt.executeQuery();

				while (lenght.next()) {
					if (lenghtEnglish > lenght.getInt("quantidade")) {
						JOptionPane.showMessageDialog(null,
								"You have finished all questions, please, try another course!");
					}
				}

				stmt = db.con.prepareStatement(SQL);
				rs = stmt.executeQuery();

				while (rs.next()) {

					vetor[0] = rs.getString("answer0");
					vetor[1] = rs.getString("answer1");
					vetor[2] = rs.getString("answer2");
					vetor[3] = rs.getString("answer3");
					int aleatorio = 10;
					int[] aux = new int[4];

					aleatorio = rand.nextInt(4);
					answer1.setText(String.valueOf(vetor[aleatorio]));
					aux[0] = aleatorio;

					while (aux[0] == aleatorio)
						aleatorio = rand.nextInt(4);
					answer2.setText(String.valueOf(vetor[aleatorio]));
					aux[1] = aleatorio;

					while (aux[0] == aleatorio || aux[1] == aleatorio)
						aleatorio = rand.nextInt(4);
					answer3.setText(String.valueOf(vetor[aleatorio]));
					aux[2] = aleatorio;

					while (aux[0] == aleatorio || aux[1] == aleatorio || aux[2] == aleatorio)
						aleatorio = rand.nextInt(4);
					answer4.setText(String.valueOf(vetor[aleatorio]));

					question
							.setText("Question " + lenghtEnglish + " - " + String.valueOf(rs.getString("question")));

					az++;

				}
			} catch (Exception error) {
				System.err.println("Error:" + error.getMessage());
				System.err.println("Error:" + error.toString());

			}

		}
	}

	public void actionsEnglish() {
		try {
			SQL = "SELECT question, answer0, answer1, answer2, answer3, explanation FROM englishExercises WHERE codQuestion="
					+ lenghtEnglish + ";";

			stmt = db.con.prepareStatement(SQL);
			lenght = stmt.executeQuery();
			while (lenght.next())
				explanation = String.valueOf(lenght.getString("explanation"));

			answer1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtEnglish++;
						if (answer1.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);
						}

						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			answer2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtEnglish++;
						if (answer2.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);

// objMath.explanationQuestion(i);
						}

						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			answer3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtEnglish++;
						if (answer3.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);

// objMath.explanationQuestion(i);
						}
						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			answer4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtEnglish++;
						if (answer4.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							JOptionPane.showMessageDialog(null, "You don't hit the correct answer!");
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);

// objMath.explanationQuestion(i);
						}
						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			continueQuestionsMath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					answer1.setEnabled(true);
					answer2.setEnabled(true);
					answer3.setEnabled(true);
					answer4.setEnabled(true);
					continueQuestionsMath.setEnabled(false);
					methodReceiverEnglish();
				}
			});

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

//========================================================

	public void methodReceiverProgramming() {
		if (db.getConnection()) {
			try {
				SQL = "SELECT question, answer0, answer1, answer2, answer3, explanation FROM programmingExercises WHERE codQuestion="
						+ lenghtProgramming + ";";
				count = "SELECT COUNT(question) AS quantidade FROM programmingExercises;";

				stmt = db.con.prepareStatement(count);
				lenght = stmt.executeQuery();

				while (lenght.next()) {
					if (lenghtProgramming > lenght.getInt("quantidade")) {
						JOptionPane.showMessageDialog(null,
								"You have finished all questions, please, try another course!");
					}
				}

				stmt = db.con.prepareStatement(SQL);
				rs = stmt.executeQuery();

				while (rs.next()) {

					vetor[0] = rs.getString("answer0");
					vetor[1] = rs.getString("answer1");
					vetor[2] = rs.getString("answer2");
					vetor[3] = rs.getString("answer3");
					int aleatorio = 10;
					int[] aux = new int[4];

					aleatorio = rand.nextInt(4);
					answer1.setText(String.valueOf(vetor[aleatorio]));
					aux[0] = aleatorio;

					while (aux[0] == aleatorio)
						aleatorio = rand.nextInt(4);
					answer2.setText(String.valueOf(vetor[aleatorio]));
					aux[1] = aleatorio;

					while (aux[0] == aleatorio || aux[1] == aleatorio)
						aleatorio = rand.nextInt(4);
					answer3.setText(String.valueOf(vetor[aleatorio]));
					aux[2] = aleatorio;

					while (aux[0] == aleatorio || aux[1] == aleatorio || aux[2] == aleatorio)
						aleatorio = rand.nextInt(4);
					answer4.setText(String.valueOf(vetor[aleatorio]));

					question.setText(
							"Question " + lenghtProgramming + " - " + String.valueOf(rs.getString("question")));

					az++;

				}
			} catch (Exception error) {
				System.err.println("Error:" + error.getMessage());
				System.err.println("Error:" + error.toString());

			}

		}
	}

	public void actionsProgramming() {
		try {
			SQL = "SELECT question, answer0, answer1, answer2, answer3, explanation FROM programmingExercises WHERE codQuestion="
					+ lenghtProgramming + ";";

			stmt = db.con.prepareStatement(SQL);
			lenght = stmt.executeQuery();
			while (lenght.next())
				explanation = String.valueOf(lenght.getString("explanation"));

			answer1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtProgramming++;
						if (answer1.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);
						}

						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			answer2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtProgramming++;
						if (answer2.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);

// objMath.explanationQuestion(i);
						}

						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			answer3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtProgramming++;
						if (answer3.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);

// objMath.explanationQuestion(i);
						}
						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			answer4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						lenghtProgramming++;
						if (answer4.getText().equals(vetor[0])) {
							JOptionPane.showMessageDialog(null, "You hit the correct answer!");
						} else {
							JOptionPane.showMessageDialog(null, "You don't hit the correct answer!");
							question.setText("Explanation of this question: " + explanation);
							question.setForeground(Color.RED);

// objMath.explanationQuestion(i);
						}
						answer1.setEnabled(false);
						answer2.setEnabled(false);
						answer3.setEnabled(false);
						answer4.setEnabled(false);
						continueQuestionsMath.setEnabled(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});

			continueQuestionsMath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					answer1.setEnabled(true);
					answer2.setEnabled(true);
					answer3.setEnabled(true);
					answer4.setEnabled(true);
					continueQuestionsMath.setEnabled(false);
					methodReceiverProgramming();
				}
			});

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}