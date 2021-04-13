package frontend;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import analysis.AnalysisType;
import selection.selection;
import viewers.viewsServer;

public class MainUI extends JFrame {
	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The selection that should reflect all changes made to the main ui
	 */
	private static selection selection = new selection();

	/**
	 * The instance of the MainUI, employing the Singleton design pattern
	 */
	private static MainUI instance;

	/**
	 * The viewsServer that will handle the displaying of views
	 */
	private static viewsServer viewserver = new viewsServer(selection);

	/**
	 * The panel that will contain the views
	 */
	private static JPanel west;

	/**
	 * A way to invoke the obscured constructor without letting the client create
	 * more than one MainUI
	 * 
	 * @return the created (or already existant) MainUI
	 */
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	/**
	 * Constructor for MainUI and all associated panels, frames, action listeners,
	 * etc.
	 */
	private MainUI() {
		// Set window title
		super("Country Statistics");

		setPreferredSize(new Dimension(1920, 1080));
		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		String[] countries = new String[] { "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
				"Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
				"Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize",
				"Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island",
				"Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi",
				"Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad",
				"Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
				"Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
				"Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
				"Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
				"Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France",
				"France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon",
				"Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe",
				"Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands",
				"Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia",
				"Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
				"Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of",
				"Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia",
				"Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
				"Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali",
				"Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico",
				"Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat",
				"Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
				"New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island",
				"Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea",
				"Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion",
				"Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
				"Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia",
				"Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia",
				"Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain",
				"Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname",
				"Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
				"Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo",
				"Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
				"Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom",
				"United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu",
				"Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
				"Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe",
				"Palestine" };
		JComboBox<String> countriesList = new JComboBox<String>(countries);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 1962; i--) {
			years.add("" + i);
		}
		Vector<String> years2 = new Vector<String>();
		for (int i = 2020; i >= 1962; i--) {
			years2.add("" + i);
		}
		JComboBox<String> fromList = new JComboBox<String>(years2);
		JComboBox<String> toList = new JComboBox<String>(years);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");

		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Scatter Chart");
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Report");
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("School enrollment, primary and secondary vs literacy rate, adult total");
		methodsNames.add(
				"Control of Corruption (-2.5-2.5) vs Proportion of seats held by women in national parliaments (%)");
		methodsNames.add("Literacy rate adult total % vs Government expenditure on education %");
		methodsNames.add("Estimated control of corruption vs GDP growth");
		methodsNames.add("Government expenditure on education, total (% of GDP) for the selected years");
		methodsNames.add("Agricultural land(%) vs forest area (%)");
		methodsNames.add("Control of corruption vs access to electricity");
		methodsNames.add("Mean drought index vs GDP growth");

		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();

		// Set charts region
		this.west = viewserver.getPanel();
		west.setLayout(new GridLayout(2, 0));

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);

		countriesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("country listener");
				JComboBox countriesList = (JComboBox) e.getSource();
				boolean valid = selection.validateCountry((String) countriesList.getSelectedItem());
				if (valid) {
					selection.setCountry((String) countriesList.getSelectedItem());
				} else {
					countriesList.setSelectedItem(selection.getCountry());
					JOptionPane.showMessageDialog(null, "Invalid Country Selection.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		fromList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox fromList = (JComboBox) e.getSource();
				selection.setSelectionYearStart(Integer.parseInt((String) fromList.getSelectedItem()));
				System.out.println("from listener");
			}
		});
		toList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("to listener");
				JComboBox toList = (JComboBox) e.getSource();
				boolean valid = selection.validateYears(selection.getYearStart(),
						Integer.parseInt((String) toList.getSelectedItem()));
				if (valid) {
					selection.setSelectionYearEnd(Integer.parseInt((String) toList.getSelectedItem()));
				} else {
					toList.setSelectedItem(String.valueOf(selection.getYearEnd()));
					JOptionPane.showMessageDialog(null, "Invalid Year Selection.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		methodsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox methodsList = (JComboBox) e.getSource();
				System.out.println("entered listener");
				if (((selection.getAnalysisType()) != (methodsList.getSelectedIndex() + 1))) {
					System.out.println("entered if");
					selection.setAnalysisType(methodsList.getSelectedIndex() + 1);
					selection.setAType(new AnalysisType(methodsList.getSelectedIndex() + 1));
					for(int i = 0; i < selection.getViewType().size(); i++) {
						viewserver.removeView(selection.getSelectedViews().get(i));
					}
					selection.clearViewType();
					viewserver.update(selection);
					instance.repaint();
				}
			}
		});
		// recalculate
		recalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewserver.update(selection);
				west = viewserver.getPanel();
				instance.setVisible(true);
				instance.repaint();
			}
		});
		// + button
		addView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valid = selection.validateViewer(selection.getToSelect());
				if (valid) {
					selection.addViewType(selection.getToSelect());
					viewserver.addView(selection.getToSelect());
					viewserver.update(selection);
					instance.setVisible(true);
					instance.repaint();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Viewer Selection.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		// - button
		removeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selection.getViewType().contains(selection.getToSelect())) {
					selection.removeViewType(selection.getToSelect());
					viewserver.removeView(selection.getToSelect());
					instance.repaint();
				} else {
					JOptionPane.showMessageDialog(null, "Viewer Not In List.", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		viewsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("views listener");
				JComboBox viewsList = (JComboBox) e.getSource();
				selection.setToSelect((String) viewsList.getSelectedItem());
			}
		});
	}

	/**
	 * Returns the "west" panel that contains the views
	 * 
	 * @return the "west" panel that contains the views
	 */
	private JPanel getWest() {
		return this.west;
	}
	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage, reportMessage2;

		reportMessage = "Mortality vs Expenses & Hospital Beds\n" + "==============================\n" + "Year 2018:\n"
				+ "\tMortality/1000 births => 5.6\n" + "\tHealth Expenditure per Capita => 10624\n"
				+ "\tHospital Beds/1000 people => 2.92\n" + "\n" + "Year 2017:\n" + "\tMortality/1000 births => 5.7\n"
				+ "\tHealth Expenditure per Capita => 10209\n" + "\tHospital Beds/1000 people => 2.87\n" + "\n"
				+ "Year 2016:\n" + "\tMortality/1000 births => 5.8\n" + "\tHealth Expenditure per Capita => 9877\n"
				+ "\tHospital Beds/1000 people => 2.77\n";

		reportMessage2 = "Unemployment: Mev vs Women\n" + "==========================\n" + "Men=>\n"
				+ "\tEmployed: 96.054%\n" + "\tUnemployed: 3.946%\n" + "\n" + "Women=>\n" + "\tEmployed: 96.163%\n"
				+ "\tUnemployed: 3.837%\n";

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

}