package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;


public class GraphAlgoTest {
	
	
	@Test
	void saveTest() throws IOException {
		graph g1 = new DGraph();
		Graph_Algo algo = new Graph_Algo(g1);
		algo.init(g1);
		
		for(int i = 1; i <= 20; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g1.addNode(tmp);
		}
		
		for(int i = 2; i < 12; i++) {
			g1.connect(1,i,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g1.connect(i,19,Math.random()*10);
		}
		
		algo.save("algo.txt");
		Graph_Algo graphInit = new Graph_Algo();;
		graphInit.init("algo.txt");
		graph g2 = graphInit.copy();
		
		Iterator<node_data> iter1 = g1.getV().iterator();
		Iterator<node_data> iter2 = g2.getV().iterator();
		Collection<node_data> list1 = g1.getV();
		Collection<node_data> list2 = g2.getV();
		
		boolean check = list1.size()==list2.size();
		
		if(check) {
			while(iter1.hasNext() && iter2.hasNext() && check) {
				node_data next1 = iter1.next();
				node_data next2 = iter2.next();
				
				if(next1.getKey()!=next2.getKey() || next1.getLocation().x() != next2.getLocation().x() ||
						next1.getLocation().y() != next2.getLocation().y()) {
					
					check = false;
				}	
			}
			iter1 = g1.getV().iterator();
			iter2 = g2.getV().iterator();
					
			while(iter1.hasNext() && iter2.hasNext() && check) {
				node_data next1 = iter1.next();
				node_data next2 = iter2.next();
				
				Collection<edge_data> edges1 = g1.getE(next1.getKey());
				Collection<edge_data> edges2 = g2.getE(next2.getKey());
				
				check = edges1.size() == edges2.size();
				
				Iterator<edge_data> iterC1 = edges1.iterator();
				Iterator<edge_data> iterC2 = edges2.iterator();
				
				while(iterC1.hasNext() && iterC2.hasNext() && check) {
					edge_data nextE1 = iterC1.next();
					edge_data nextE2 = iterC2.next();
					
					if(nextE1.getSrc()!=nextE2.getSrc() || nextE1.getDest()!=nextE2.getDest() || nextE1.getWeight()!=nextE2.getWeight())
						check = false;
				}
			}
		}
		assertEquals(check,true);
	}
	
	@Test
	void isConnected1() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);
		
		for(int i = 1; i <= 7; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,Math.random());
		g.connect(1,3,Math.random());
		g.connect(1,4,Math.random());
		g.connect(2,1,Math.random());
		g.connect(2,4,Math.random());
		g.connect(2,5,Math.random());
		g.connect(3,6,Math.random());
		g.connect(4,3,Math.random());
		g.connect(4,6,Math.random());
		g.connect(4,7,Math.random());
		g.connect(5,4,Math.random());
		g.connect(5,7,Math.random());
		g.connect(7,6,Math.random());
		
		boolean conect = algo.isConnected();
		assertEquals(conect,false);
	}
	
	@Test
	void isConnected2() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 7; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,Math.random());
		g.connect(1,7,Math.random());
		g.connect(2,3,Math.random());
		g.connect(3,4,Math.random());
		g.connect(3,1,Math.random());
		g.connect(4,5,Math.random());
		g.connect(4,7,Math.random());
		g.connect(5,6,Math.random());
		g.connect(5,3,Math.random());
		g.connect(6,1,Math.random());
		g.connect(7,5,Math.random());
		
		boolean conect = algo.isConnected();
		assertEquals(conect,true);
	}
	
	@Test
	void isConnected3() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 7; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,Math.random());
		g.connect(1,7,Math.random());
		g.connect(2,3,Math.random());
		g.connect(3,4,Math.random());
		g.connect(3,1,Math.random());
		g.connect(4,5,Math.random());
		g.connect(4,7,Math.random());
		g.connect(5,6,Math.random());
		g.connect(5,3,Math.random());
		//g.connect(6,1,Math.random()); I remove this edge and because of that the graph is not strongly connected
		g.connect(7,5,Math.random());
		
		boolean conect = algo.isConnected();
		assertEquals(conect,false);
	}
	
	@Test
	void shortesPathDist1() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 4; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,10);
		g.connect(1,3,2);
		g.connect(1,4,6);
		g.connect(2,4,7);
		g.connect(3,4,3);
		
		double distance  = algo.shortestPathDist(1, 4);
		assertEquals(distance,5);
		
	}
	
	@Test
	void shortesPathDist2() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 6; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,5,0.1);
		g.connect(1,6,0.9);
		g.connect(2,1,0.3);
		g.connect(2,3,0.3);
		g.connect(2,4,0.4);
		g.connect(3,4,0.6);
		g.connect(4,5,1);
		g.connect(5,1,0.55);
		g.connect(5,6,0.45);
		g.connect(6,4,1);
		g.connect(3,5,0.4);

		
		double distance  = algo.shortestPathDist(2, 6);
		distance = ((int)(distance*100.0))/100.0;
		assertEquals(distance,0.85);
		
	}
	
	@Test
	void shortesPathDist3() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 6; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,8);
		g.connect(1,4,18);
		g.connect(2,3,20);
		g.connect(2,4,7);
		g.connect(2,5,1);
		g.connect(3,4,2);
		g.connect(3,6,7);
		g.connect(4,2,1);
		g.connect(5,6,6);

		
		double distance  = algo.shortestPathDist(1, 6);
		assertEquals(distance,15);
		
	}
	
	@Test
	void shortesPath1() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 6; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,8);
		g.connect(1,4,18);
		g.connect(2,3,20);
		g.connect(2,4,7);
		g.connect(2,5,1);
		g.connect(3,4,2);
		g.connect(3,6,7);
		g.connect(4,2,1);
		g.connect(5,6,6);

		
		List<Integer> expected = new LinkedList<>();
		expected.add(1);
		expected.add(2);
		expected.add(5);
		expected.add(6);
		
		List<node_data> result  = algo.shortestPath(1, 6);
		
		int index = 0;
		boolean check = true;
		for(node_data v : result) {
			if(result.get(index).getKey()!=v.getKey() && check) {
				check = false;
			}
			index++;
		}
		assertEquals(check, true);
		
	}
	
	@Test
	void shortesPath2() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 5; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,1);	
		g.connect(1,3,3);
		g.connect(1,5,3);
		g.connect(2,3,1);
		g.connect(2,4,3);
		g.connect(3,2,2);
		g.connect(3,4,1);
		g.connect(3,1,1);
		g.connect(4,1,1);
		g.connect(4,5,2);
		g.connect(5,1,6);
		g.connect(5,4,1);
		
		List<Integer> expected = new LinkedList<>();
		expected.add(5);
		expected.add(4);
		expected.add(1);
		expected.add(2);
		
		List<node_data> result  = algo.shortestPath(5, 2);
		
		int index = 0;
		boolean check = true;
		for(node_data v : result) {
			if(result.get(index).getKey()!=v.getKey() && check) {
				check = false;
			}
			index++;
		}
		assertEquals(check, true);
		
	}
	
	@Test
	void shortesPath3() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 6; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,8.7);	
		g.connect(1,6,3.8);
		g.connect(2,5,7.5);
		g.connect(2,3,1.3);
		g.connect(3,4,2.1);
		g.connect(4,5,1.9);
		g.connect(5,2,5.8);
		g.connect(5,3,5);
		g.connect(5,4,3.1);
		g.connect(6,1,8);
		g.connect(6,5,12);
		
		List<Integer> expected = new LinkedList<>();
		expected.add(5);
		expected.add(4);
		expected.add(1);
		expected.add(2);
		
		List<node_data> result  = algo.shortestPath(3, 6);
		
		assertEquals(result, null);
		
	}
	
	@Test
	void TSP1() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 5; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,14);	
		g.connect(2,3,23);
		g.connect(2,4,13);
		g.connect(3,1,5);
		g.connect(4,1,43);
		g.connect(4,5,33);
		g.connect(5,4,11);
		g.connect(5,2,22);

		
		List<Integer> targets = new LinkedList<>();
		targets.add(5);
		targets.add(4);
		targets.add(1);
		targets.add(2);
		targets.add(3);
		
		List<Integer> expected = new LinkedList<>();
		expected.add(3);
		expected.add(1);
		expected.add(2);
		expected.add(4);
		expected.add(5);
		
		List<node_data> result  = algo.TSP(targets);
		
		int index = 0;
		boolean check = true;
		for(node_data v : result) {
			if(result.get(index).getKey()!=v.getKey() && check) {
				check = false;
			}
			index++;
		}
		assertEquals(check, true);
	}
	
	@Test
	void TSP2() {
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 8; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,4);	
		g.connect(2,7,4);
		g.connect(3,4,6);
		g.connect(3,8,6);
		g.connect(4,3,3);
		g.connect(4,7,8);
		g.connect(5,6,3);
		g.connect(6,5,6);
		g.connect(6,7,7);
		g.connect(8,1,3);
		g.connect(8,3,5);
		g.connect(8,5,2);
		g.connect(7,2,3);
		g.connect(7,6,2);
		
		List<Integer> targets = new LinkedList<>();
		targets.add(3);
		targets.add(5);
		targets.add(1);
		

		
		List<Integer> expected = new LinkedList<>();
		expected.add(3);
		expected.add(8);
		expected.add(1);
		expected.add(2);
		expected.add(7);
		expected.add(6);
		expected.add(5);
		
		List<node_data> result  = algo.TSP(targets);
		
		int index = 0;
		boolean check = true;
		for(node_data v : result) {
			if(result.get(index).getKey()!=v.getKey() && check) {
				check = false;
			}
			index++;
		}
		assertEquals(check, true);
	}
	
	@Test
	void TSP3(){
		graph g = new DGraph();
		Graph_Algo algo = new Graph_Algo(g);

		
		for(int i = 1; i <= 10; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		g.connect(1,2,10);	
		g.connect(1,6,5);
		g.connect(2,3,3);
		g.connect(2,5,3);
		g.connect(3,4,4);
		g.connect(3,8,5);
		g.connect(4,9,4);
		g.connect(5,3,4);
		g.connect(5,7,2);
		g.connect(6,2,3);
		g.connect(6,10,2);
		g.connect(7,4,7);
		g.connect(8,4,4);
		g.connect(8,9,3);
		g.connect(10,2,6);
		g.connect(10,7,8);
		
		List<Integer> targets = new LinkedList<>();
		targets.add(9);
		targets.add(1);
		targets.add(5);
		

		
		List<Integer> expected = new LinkedList<>();
		expected.add(1);
		expected.add(6);
		expected.add(2);
		expected.add(5);
		expected.add(3);
		expected.add(8);
		expected.add(9);
		
		List<node_data> result  = algo.TSP(targets);
		
		int index = 0;
		boolean check = true;
		for(node_data v : result) {
			if(result.get(index).getKey()!=v.getKey() && check) {
				check = false;
			}
			index++;
		}
		assertEquals(check, true);
	}
	
	@Test
	void TestCopy(){
		graph g1 = new DGraph();
		Graph_Algo algo = new Graph_Algo(g1);
		
		
		for(int i = 1; i <= 10; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g1.addNode(tmp);
		}
		g1.connect(1,2,10);	
		g1.connect(1,6,5);
		g1.connect(2,3,3);
		g1.connect(2,5,3);
		g1.connect(3,4,4);
		g1.connect(3,8,5);
		g1.connect(4,9,4);
		g1.connect(5,3,4);
		g1.connect(5,7,2);
		g1.connect(6,2,3);
		g1.connect(6,10,2);
		g1.connect(7,4,7);
		g1.connect(8,4,4);
		g1.connect(8,9,3);
		g1.connect(10,2,6);
		g1.connect(10,7,8);
		
		graph g2 = algo.copy();
		
		g1.connect(6, 3, 21);
		
		Iterator<node_data> iter1 = g1.getV().iterator();
		Iterator<node_data> iter2 = g2.getV().iterator();
		Collection<node_data> list1 = g1.getV();
		Collection<node_data> list2 = g2.getV();
		
		boolean check = list1.size()==list2.size();
		
		if(check) {
			while(iter1.hasNext() && iter2.hasNext() && check) {
				node_data next1 = iter1.next();
				node_data next2 = iter2.next();
				
				if(next1.getKey()!=next2.getKey() || next1.getLocation().x() != next2.getLocation().x() ||
						next1.getLocation().y() != next2.getLocation().y()) {
					
					check = false;
				}	
			}
			iter1 = g1.getV().iterator();
			iter2 = g2.getV().iterator();
					
			while(iter1.hasNext() && iter2.hasNext() && check) {
				node_data next1 = iter1.next();
				node_data next2 = iter2.next();
				
				Collection<edge_data> edges1 = g1.getE(next1.getKey());
				Collection<edge_data> edges2 = g2.getE(next2.getKey());
				
				check = edges1.size() == edges2.size();//giving a false here because the number of edges 
				
				Iterator<edge_data> iterC1 = edges1.iterator();
				Iterator<edge_data> iterC2 = edges2.iterator();
				
				while(iterC1.hasNext() && iterC2.hasNext() && check) {
					edge_data nextE1 = iterC1.next();
					edge_data nextE2 = iterC2.next();
					
					if(nextE1.getSrc()!=nextE2.getSrc() || nextE1.getDest()!=nextE2.getDest() || nextE1.getWeight()!=nextE2.getWeight())
						check = false;
				}
			}
		}
		assertEquals(check,false);
	}
	
	@Test
	void TestInit() throws IOException{
		graph g1 = new DGraph();
		Graph_Algo algo = new Graph_Algo();
		algo.init("algo.txt");
		
		for(int i = 1; i <= 10; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g1.addNode(tmp);
		}
		g1.connect(1,2,10);	
		g1.connect(1,6,5);
		g1.connect(2,3,3);
		g1.connect(2,5,3);
		g1.connect(3,4,4);
		g1.connect(3,8,5);
		g1.connect(4,9,4);
		g1.connect(5,3,4);
		g1.connect(5,7,2);
		g1.connect(6,2,3);
		g1.connect(6,10,2);
		g1.connect(7,4,7);
		g1.connect(8,4,4);
		g1.connect(8,9,3);
		g1.connect(10,2,6);
		g1.connect(10,7,8);
		
		graph g2 = algo.copy();
		
		node_data tmp = new Vertex(11,Math.random(),Math.random());
		g1.addNode(tmp);
		
		Iterator<node_data> iter1 = g1.getV().iterator();
		Iterator<node_data> iter2 = g2.getV().iterator();
		Collection<node_data> list1 = g1.getV();
		Collection<node_data> list2 = g2.getV();
		
		boolean check = list1.size()==list2.size();// giving here false
		
		if(check) {
			while(iter1.hasNext() && iter2.hasNext() && check) {
				node_data next1 = iter1.next();
				node_data next2 = iter2.next();
				
				if(next1.getKey()!=next2.getKey() || next1.getLocation().x() != next2.getLocation().x() ||
						next1.getLocation().y() != next2.getLocation().y()) {
					
					check = false;
				}	
			}
			iter1 = g1.getV().iterator();
			iter2 = g2.getV().iterator();
					
			while(iter1.hasNext() && iter2.hasNext() && check) {
				node_data next1 = iter1.next();
				node_data next2 = iter2.next();
				
				Collection<edge_data> edges1 = g1.getE(next1.getKey());
				Collection<edge_data> edges2 = g2.getE(next2.getKey());
				
				check = edges1.size() == edges2.size();
				
				Iterator<edge_data> iterC1 = edges1.iterator();
				Iterator<edge_data> iterC2 = edges2.iterator();
				
				while(iterC1.hasNext() && iterC2.hasNext() && check) {
					edge_data nextE1 = iterC1.next();
					edge_data nextE2 = iterC2.next();
					
					if(nextE1.getSrc()!=nextE2.getSrc() || nextE1.getDest()!=nextE2.getDest() || nextE1.getWeight()!=nextE2.getWeight())
						check = false;
				}
			}
		}
		assertEquals(check,false);
	}
}
