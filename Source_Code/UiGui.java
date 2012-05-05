
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.util.Scanner;

class UiGui extends Ui
{
	private final int BASEX = 120, BASEY = 60; // base point for head

	public UiGui()
	{
		setBackground (Color.blue);
		setPreferredSize (new Dimension(1000, 500));
	}

	public void paintComponent (Graphics page)
	{
		super.paintComponent (page);

		String url = UrlCard.getAllUrls().get((int)(Math.random() * UrlCard.getAllUrls().size())).prettyPrint();

		int xcoor = 50, ycoor = 5;

		page.setColor (Color.yellow);
		page.fillRect (BASEX-xcoor, BASEY, url.length() * 5, 80); // head
		page.setColor (Color.black);

		Scanner lineScan = new Scanner (url);
		lineScan.useDelimiter("\n");

		while (lineScan.hasNext())
		{
			page.drawString (lineScan.next(), BASEX-xcoor, BASEY+ycoor+15);
			ycoor += 20;
		}
	}

	public void viewRandomUrlCard()
	{
		
		JFrame frame = new JFrame ("Random Url Card");
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		
		UiGui panel = new UiGui();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}