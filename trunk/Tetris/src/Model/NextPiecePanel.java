package Model;

import java.util.Random;

import javax.swing.JPanel;

public class NextPiecePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Structure nextPiece;
	

	public NextPiecePanel() {
		this.setBounds(0, 0, 200, 200);
		this.setLayout(null);
		this.setOpaque(false);
	}

	public Structure getNextPiece() {
		return nextPiece;
	}
	
	public void setNextPiece(TStructure nextPiece) {
		this.nextPiece = nextPiece;
	}
	
private Structure getRandomStructure() {
		
		Random gen = new Random();
		int x = gen.nextInt(7);
//		x=3; //for testing
		switch (x) {
		case 0:
			return new TStructure(0, 0, this);
		case 1:
			return new SquareStructure(0, 0, this);
		case 2:
			return new StickStructure(0, 0, this);
		case 3:
			return new ZStructure(0, 0, this);
		case 4:
			return new SStructure(0, 0, this);
		case 5:
			return new LRightStructure(0, 0, this);
		default:
			return new LLeftStructure(0, 0, this);
		}
	}

	public void addNewStructure() {
		nextPiece = getRandomStructure();
		this.removeAll();
		add(nextPiece);
		repaint();
	}
}
