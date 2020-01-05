package gui_graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JFrame;

import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.graph;
import dataStructure.node_data;

public class Graph_GUI extends JFrame implements ActionListener, MouseListener{


		private graph g;
		LinkedList<node_data> points = new LinkedList<node_data>();
		
		public Graph_GUI() {
			g = new DGraph();
			points = (LinkedList<node_data>) g.getV();
			initGUI();
		}
		
		private void initGUI() 
		{
			this.setSize(500, 500);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// project terminates when exiting the window
			
			MenuBar menuBar = new MenuBar();
			Menu menu = new Menu("Menu");
			menuBar.add(menu);
			this.setMenuBar(menuBar);
			
			MenuItem item1 = new MenuItem(" Load ");
			item1.addActionListener(this);
			
			MenuItem item2 = new MenuItem(" Save ");
			item2.addActionListener(this);
			
			MenuItem item3 = new MenuItem(" Is connected ");
			item3.addActionListener(this);
			
			MenuItem item4 = new MenuItem(" Shortest Path Distance ");
			item4.addActionListener(this);
			
			MenuItem item5 = new MenuItem(" Travel Salesman Problem ");
			item5.addActionListener(this);
			
			menu.add(item1);
			menu.add(item2);
			menu.add(item3);
			menu.add(item4);
			menu.add(item5);
			
			this.addMouseListener(this);
		}

		public void paint(Graphics g)
		{
			super.paint(g);
			
			node_data prev = null;
			
			for (node_data p : points) 
			{
				g.setColor(Color.BLUE);
				g.fillOval((int)p.getLocation().x(), (int)p.getLocation().y(), 10, 10);
				
				if(prev != null)
				{
					g.setColor(Color.RED);
					g.drawLine((int)p.getLocation().x(), (int)p.getLocation().y(), (int)prev.getLocation().x(), (int)prev.getLocation().y());
					g.drawString("5", (int)((p.getLocation().x()+prev.getLocation().x())/2),(int)((p.getLocation().y()+prev.getLocation().y())/2));
				}
				
				prev = p;
			}
		}
		
		

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String str = e.getActionCommand();
			
			if(str.equals("Item 1"))
			{
				node_data p1 = new Vertex(100,100);
				node_data p2 = new Vertex(50,300);
				node_data p3 = new Vertex(400,150);
				
				points.add(p1);
				points.add(p2);
				points.add(p3);
				
				repaint();
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			node_data p = new Vertex(x,y);
			points.add(p);
			repaint();
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			node_data p = new Vertex(x,y);
			points.add(p);
			repaint();			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//System.out.println("mouseEntered");
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//System.out.println("mouseExited");
		}
	}

	
	

