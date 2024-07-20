import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

// this class is used to build the graphics and the game logic
class CarGame extends JFrame implements KeyListener,ActionListener
{

	private int position_x=300; // x position of the car 
	private int position_y=700; // y position of the car
	private ImageIcon car; // car image 
	private Timer timer; // timer to update the screen
	Random random=new Random(); // random number generator
	
	private int num1=400,num2=0,num3=0; // x position of the obstacles
	private int tree1position_y=400,tree2position_y=-200,tree3position_y=-500,tree4position_y=100,tree5position_y=-300,tree6position_y=500; // y position of the obstacles 
	private int roadmove=0; // y position of the road
	private int carposition_x[]={100,200,300,400,500}; // x position of the car
	private int carposition_y[]= {-240,-480,-720,-960,-1200}; // y position of the car
	private int cposition_x1=0,cposition_x2=2,cposition_x3=4; // x position of the car
	private int cposition_y1=random.nextInt(5),cposition_y2=random.nextInt(5),cposition_y3=random.nextInt(5); // y position of the car
	int y1pos=carposition_y[cposition_y1],y2pos=carposition_y[cposition_y2],y3pos=carposition_y[cposition_y3]; // y position of the car
	private ImageIcon car1,car2,car3; // car image
	private int score=0,delay=100,speed=90; // score,delay and speed of the game
	private ImageIcon tree1,tree2,tree3; // tree image
	private boolean rightrotate=false,gameover=false,paint=false; // boolean variables to control the game logic and the graphics
	
	// constructor to initialize the game
	public CarGame(String title)
	{
		super(title); // call the constructor of the parent class JFrame
		setBounds(300,10,700,700); // set the position and size of the frame
		setVisible(true); // make the frame visible
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the frame when the close button is clicked
		setLayout(null); // set the layout of the frame to null
		addKeyListener(this); // add the key listener to the frame
		setFocusable(true); // set the focus to the frame
		setResizable(false); // set the frame to be non resizable
		
	}

	// this method is used to paint the graphics on the screen 
	public void paint(Graphics g)
	{
		g.setColor(new Color(0X82CD47)); // set the color of the grass
		g.fillRect(0, 0, 700, 700);  // draw the grass
		g.setColor(new Color(0X9F8772)); // set the color of the road
		g.fillRect(90,0,10,700); // draw the road
		g.fillRect(600, 0, 10, 700); 
		g.fillRect(100, 0, 500, 700);
		
	// draw the road lines 
	if(roadmove==0)
	{
		for(int i=0; i<=700; i+=100) 
		{ 	// draw the road lines
			g.setColor(Color.white);
			g.fillRect(50, i,10, 70); // 
	
		}
		roadmove=1; // set the roadmove to 1
	}
	else if(roadmove==1)
	{ // draw the road lines again for the next frame
		for(int i=50; i<=700; i+=100)
		{
			g.setColor(Color.white);
			g.fillRect(350, i,10, 70);
		}
		roadmove=0; // set the roadmove to 0
	}
	
	try {
		tree1=new ImageIcon(ImageIO.read(getClass().getResource("tree.png"))); // load the tree image
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	try {
		tree2=new ImageIcon(ImageIO.read(getClass().getResource("tree.png"))); // load the tree image
	} catch (IOException e) {
		e.printStackTrace();
	}

	try {
		tree3=new ImageIcon(ImageIO.read(getClass().getResource("tree.png"))); // load the tree image
	} catch (IOException e) {
		e.printStackTrace();
	}

	tree1.paintIcon(this, g, 0, tree1position_y); // draw the tree image on the screen
	num1=random.nextInt(500); // generate a random number
	tree1position_y+=50; // increment the y position of the tree
	
	tree2.paintIcon(this, g, 0,tree2position_y ); // draw the tree image on the screen
	tree2position_y+=50; // increment the y position of the tree
	
	tree3.paintIcon(this,g,0,tree3position_y); // draw the tree image on the screen
	tree3position_y+=50; // increment the y position of the tree
	tree1.paintIcon(this,g,600,tree4position_y);
	tree4position_y+=50;
	tree3.paintIcon(this, g,600,tree5position_y);
	tree5position_y+=50;
	tree2.paintIcon(this, g,600,tree6position_y);
	tree6position_y+=50;
	
		
	if(tree1position_y>700)
	{ // if the tree goes out of the screen then reset the tree
		num1=random.nextInt(500); // generate a random number
		tree1position_y=-num1; // reset the y position of the tree
	}
	if(tree2position_y>700)
	{ 
		num1=random.nextInt(500);
		tree2position_y=-num1;
	}
	if(tree3position_y>700)
	{
		num1=random.nextInt(500);
		tree3position_y=-num1;
	}
	if(tree4position_y>700)
	{ // if the tree goes out of the screen then reset the tree
		num1=random.nextInt(500);
		tree4position_y=-num1;
	}
	if(tree5position_y>700)
	{
		num1=random.nextInt(500);
		tree5position_y=-num1;
	}
	if(tree6position_y>700)
	{ // if the tree goes out of the screen then reset the tree
		num1=random.nextInt(500);
		tree6position_y=-num1;
	}
	
	
		// load image for car
		try {
			car=new ImageIcon(ImageIO.read(getClass().getResource("gamecar3.png"))); // load the car image 
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		// car=new ImageIcon("gamecar1.png");
		car.paintIcon(this,g,position_x,position_y); // draw the car image on the screen
		
		position_y-=40;
		if(position_y<500)
		{
		position_y=500;	
		}
		
		// load the opponent image for car
		try {
			car1=new ImageIcon(ImageIO.read(getClass().getResource("gamecar1.png")));
		} catch (IOException e) {
				e.printStackTrace();
		}
		// load the opponent image for car
		try {
			car2=new ImageIcon(ImageIO.read(getClass().getResource("gamecar2.png")));
		} catch (IOException e) {
				e.printStackTrace();
		}
		// load the opponent image for car	
		try {
			car3=new ImageIcon(ImageIO.read(getClass().getResource("gamecar4.png")));
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		car1.paintIcon(this, g, carposition_x[cposition_x1], y1pos); // draw the opponent car image on the screen
		car2.paintIcon(this, g, carposition_x[cposition_x2], y2pos); 
		car3.paintIcon(this, g, carposition_x[cposition_x3], y3pos);
		y1pos+=50; // increment the y position of the opponent car
		y2pos+=50;
		y3pos+=50;
		if(y1pos>700)
		{ // if the opponent car goes out of the screen then reset the opponent car
			cposition_x1=random.nextInt(5); // generate a random number
			cposition_y1=random.nextInt(5); 	
			y1pos=carposition_y[cposition_y1]; // reset the y position of the opponent car
			
		}
		if(y2pos>700)
		{ // if the opponent car goes out of the screen then reset the opponent car
			cposition_x2++;
			if(cposition_x2>4)
			{ 
				cposition_x2=0; 
			}
			
			cposition_x2=random.nextInt(5);
			cposition_y2=random.nextInt(5);
			y2pos=carposition_y[cposition_y2];
			
		}
		if(y3pos>700)
		{
			cposition_x3++;
			if(cposition_x3>4)
			{
				cposition_x3=0;
			}
			cposition_x3=random.nextInt(5);
			cposition_y3=random.nextInt(5);
			y3pos=carposition_y[cposition_y3];
		}
	
		if(cposition_x1==cposition_x2 && cposition_y1>-100 && cposition_y2>-100)
		{ 
			cposition_x1-=1;
			if(cposition_x1<0)
			{
				cposition_x1+=2;
			}
		}
		if(cposition_x1==cposition_x3&& cposition_y1>-100 && cposition_y3>-100)
		{
			cposition_x3-=1;
			if(cposition_x3<0)
			{
				cposition_x3+=2;
			}
		}
		if(cposition_x2==cposition_x3&& cposition_y3>-100 && cposition_y2>-100)
		{
			cposition_x2-=1;
			if(cposition_x2<0)
			{
				cposition_x2+=2;
			}
		}
		if(cposition_x1<2 && cposition_x2<2 && cposition_x3<2)
		{
			if(cposition_x1==0 && cposition_x2==0 && cposition_x3==1)
			{
				cposition_x3++;
				cposition_x2++;
			}
			else if(cposition_x1==0 && cposition_x2==1 && cposition_x3==0)
			{
				cposition_x3++;
				cposition_x2++;
			}
			else if(cposition_x1==1 && cposition_x2==0 && cposition_x3==0)
			{
				cposition_x1++;
				cposition_x2++;
			}
		}
		
		// if the opponent car hits the player car then reset the game
		if(y1pos<position_y && y1pos+175>position_y && carposition_x[cposition_x1]==position_x)
		{ 
		gameover=true;	
		}
		if(y2pos<position_y && y2pos+175>position_y && carposition_x[cposition_x2]==position_x)
		{ 
		gameover=true;	
		}
		if(y3pos<position_y  && y3pos+175>position_y && carposition_x[cposition_x3]==position_x)
		{
		gameover=true;	
		}
		if(position_y<y1pos && position_y+175>y1pos && carposition_x[cposition_x1]==position_x)
		{
		gameover=true;	
		}
		if(position_y<y2pos && position_y+175>y2pos && carposition_x[cposition_x2]==position_x)
		{
		gameover=true;	
		}
		if(position_y<y3pos  && position_y+175>y3pos && carposition_x[cposition_x3]==position_x)
		{
		gameover=true;	
		}

		//score 
		g.setColor(Color.red);
		g.fillRect(120,35,220,50);
		g.setColor(Color.black);
		g.fillRect(125,40, 210, 40);
		g.setColor(Color.red);
		g.fillRect(385,35,180,50);
		g.setColor(Color.black);
		g.fillRect(390,40, 170, 40);
		g.setColor(Color.white);
		g.setFont(new Font("MV Boli",Font.BOLD,30));
		g.drawString("Score : "+score, 130, 67);
		g.drawString(speed+" Km/h", 400, 67);
		score++; // increment the score
		speed++; // increment the speed
		if(speed>140)
		{ // if the speed is greater than 140 then reset the speed
			speed=240-delay; 
		
		}
		if(score%50==0)
		{ // if the score is divisible by 50 then increase the delay
			delay-=10;
			if(delay<60)
			{
				delay=60; // set the delay to 60
			}
		}
		//delay 
		try
		{
			
			TimeUnit.MILLISECONDS.sleep(delay); // delay the game
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(y1pos<position_y && y1pos+175>position_y && carposition_x[cposition_x1]==position_x)
		{
		gameover=true;	
		}
		if(y2pos<position_y && y2pos+175>position_y && carposition_x[cposition_x2]==position_x)
			
		{
		gameover=true;	
		}
		if(y3pos<position_y  && y3pos+175>position_y && carposition_x[cposition_x3]==position_x)
		{
		gameover=true;	
		}
		if(gameover)
		{
		g.setColor(Color.gray);
		g.fillRect(120, 210, 460, 200);	
		g.setColor(Color.DARK_GRAY);
		g.fillRect(130, 220, 440, 180);
		g.setFont(new Font("MV Boli",Font.BOLD,50));
		g.setColor(Color.red);
		g.drawString("Game Over !",210, 270);
		g.setColor(Color.white);
		g.setFont(new Font("MV Boli",Font.BOLD,30));
		g.drawString("Press Enter to Restart", 190, 340);
		if(!paint)
		{
			repaint();
			paint=true;
		}
		}
		else
		{
		repaint();
		}
	}
		
	public static void main(String args[])
	{
		CarGame c=new CarGame("Car Racing Game");
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT && !gameover)
		{ // if the left key is pressed then move the car to the left
			position_x-=100;
			if(position_x<100)
			{
				position_x=100; // set the car to the left most position
			}
			
			
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT&&!gameover)
		{ 	// if the right key is pressed then move the car to the right
			position_x+=100;
			if(position_x>500)
			{
				position_x=500; // if the car is at the right most position then don't move it
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER && gameover)
		{ // if the game is over and the enter key is pressed then restart the game
			gameover=false; 	
			paint=false;
			cposition_x1=0;
			cposition_x2=2;
			cposition_x3=4;
			cposition_y1=random.nextInt(5); // randomize the position of the opponent cars
			cposition_y2=random.nextInt(5); 
			cposition_y3=random.nextInt(5);
			y1pos=carposition_y[cposition_y1]; // set the position of the opponent cars
			y2pos=carposition_y[cposition_y2];
			y3pos=carposition_y[cposition_y3];
			speed=90; // set the speed to 90
			score=0; // set the score to 0
			delay=100; // set the delay to 100
			position_x=300; // set the position of the player car to the center
			position_y=700;	 // set the position of the player car to the bottom
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()=='a'&&!gameover)
		{ // if the key pressed is 'a' then move the car left
			position_x-=100; // decrement the position_x by 100
			
		}
		if(e.getKeyChar()=='s'&&!gameover)
		{ // if the key pressed is 's' then move the car right
			position_x+=100; // increment the position_x by 100
		}
		
		repaint();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {}
}



