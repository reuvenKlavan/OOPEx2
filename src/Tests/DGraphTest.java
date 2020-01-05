package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataStructure.DGraph;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

public class DGraphTest {
	
	
	
	@Test
	void getNode(){
		graph g = new DGraph();
		
		for(int i = 1; i <= 100; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		int find = 30;
		node_data check = g.getNode(find);
		assertEquals(find, check.getKey());
	}
	
	@Test
	void getEdge(){
		graph g = new DGraph();
		
		for(int i = 0; i < 100; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		for(int i = 0; i < 30;i++) {
			g.connect(1,100-i-1,1.0);
		}
		
		int source = 1;
		int destination = 100 - (int)(Math.random()*30);
		edge_data find = g.getEdge(source, destination);
		boolean isequal = (find.getDest() == destination && find.getSrc() == source);
		
		assertEquals(true,isequal);
	}
	

	@Test
	void addNodeAndEdge(){
		graph g = new DGraph();
		
		for(int i = 0; i < 100; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		for(int i = 0; i < 30;i++) {
			g.connect(1,100-i-1,1.0);
		}
		int check =g.getMC();
		assertEquals(check,130);
	}
	
	@Test 
	void removeNodeAndEdges() {
		graph g = new DGraph();
		
		for(int i = 0; i < 100; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		for(int i = 0; i < 30;i++) {
			g.connect(1,100-i-1,1.0);
		}
		
		int before = g.getMC();
		g.removeNode(21);
		g.removeNode(43);
		g.removeNode(37);
		g.removeNode(1);
		int after = g.getMC();
		assertEquals(164,after);		
	}
	
	
	@Test
	void removeNodeAndEdges2() {
		graph g = new DGraph();
		
		for(int i = 1; i <= 20; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		for(int i = 2; i < 12; i++) {
			g.connect(1,i,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(i,19,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(17,i,Math.random()*10);
		}
		
		for(int i = 2; i < 11; i++) {
			g.connect(i,11,Math.random()*10);
		}
		
		int before = g.getMC();
		g.removeNode(1);
		before+=13;//delete node:1, delete source edge:11, delete destination edge:1
		g.removeEdge(2,11);
		before++;
		g.removeEdge(4, 11);
		before++;
		g.removeNode(5);
		before+=4;//delete node:1, delete source edge:2, delete destination edge:1
		int after = g.getMC();
		assertEquals(before,after);
		
	}
	
	@Test
	void connect() {//check for if the graph become a multi graph or not 
		graph g = new DGraph();
		
		for(int i = 1; i <= 20; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(i,19,Math.random()*10);
		}
		
		for(int i = 10; i < 18; i++) {
			g.connect(17,i,Math.random()*10);
		}
		
		for(int i = 2; i < 15; i++) {
			g.connect(i,11,Math.random()*10);
		}
		
		
		int before = g.getMC();
		g.connect(17, 13, 4);//this is not a multi-graph
		g.connect(6,19,2);//this is not a multi-graph
		g.connect(3, 11, 1);//this is not a multi-graph
		int after = g.getMC();
		assertEquals(before,after);
	}
	
	@Test
	void addNode() {
		graph g = new DGraph();
		
		for(int i = 1; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		node_data tmp1 = new Vertex(24,Math.random(),Math.random());
		node_data tmp2= new Vertex(103,Math.random(),Math.random());
		node_data tmp3= new Vertex(203,Math.random(),Math.random());
		int before = g.getMC();
		g.addNode(tmp1);//there is a Vertex with this key
		g.addNode(tmp2);//there is a Vertex with this key
		g.addNode(tmp3);
		before++;
		int after = g.getMC();
		assertEquals(before,after);
	}
	
	@Test
	void getV() {
		graph g = new DGraph();
		
		for(int i = 1; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		Collection<node_data> vertices = g.getV();
		boolean check = true;
		Iterator<node_data> iter = vertices.iterator();
		
		while(iter.hasNext() && check) {
			node_data next = iter.next();
			node_data v = g.getNode(next.getKey());
			if(v!=next) {// I check the address to see if this is the real vertex insted of a copy
				check = false;
			}
		}
		assertEquals(check,true);
	}
	
	@Test
	void getE() {
		graph g = new DGraph();

		for(int i = 1; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		for(int i = 2; i < 12; i++) {
			g.connect(1,i,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(i,19,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(17,i,Math.random()*10);
		}
		
		for(int i = 2; i < 11; i++) {
			g.connect(i,11,Math.random()*10);
		}
		
		Collection<edge_data> edges = g.getE(1);
		boolean check = true;
		
		if(edges.size()!=11) {
			check = false;
		}
		if(check) {
			for(edge_data e : edges) {
				edge_data test = g.getEdge(e.getSrc(), e.getDest());
				if(test != e) 
					check = false;
			}	
		}
		
		assertEquals(true, check);
		
	}
	
	@Test
	void numOfVertices() {
		graph g = new DGraph();
		
		for(int i = 1; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		for(int i = 41; i <= 100; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.removeNode(tmp.getKey());
		}
		
		for(int i = 201; i <= 250; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		
		for(int i = 151; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.removeNode(tmp.getKey());
		}
		
		int numOfNode = g.nodeSize();
		assertEquals(numOfNode, 140);
	}
	
	
	@Test
	void numOfEdges() {
		graph g = new DGraph();
		
		for(int i = 1; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		

		for(int i = 2; i < 12; i++) {
			g.connect(1,i,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(i,19,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(17,i,Math.random()*10);
		}
		
		for(int i = 2; i < 11; i++) {
			g.connect(i,11,Math.random()*10);
		}
		
		int before = g.edgeSize();
		g.removeNode(17);
		before -=10;
		int after = g.edgeSize();
		assertEquals(before, after);
	}
	
	@Test
	void failGetNode() {
		graph g = new DGraph();
		
		for(int i = 1; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		node_data test = g.getNode(201);
		assertEquals(test,null);
			
	}
	
	@Test 
	void failGetEdge() {
		graph g = new DGraph();
		
		for(int i = 1; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		

		for(int i = 1; i < 12; i++) {
			g.connect(1,i,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(i,19,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(17,i,Math.random()*10);
		}
		
		for(int i = 2; i < 11; i++) {
			g.connect(i,11,Math.random()*10);
		}
		
		edge_data test = g.getEdge(1, 13);
		boolean check = test == null;
		if(check==true) {
			check = test == g.getEdge(13, 12);
		}	
		if(check==true) {	
			check = test == g.getEdge(17, 15);
		}	
		if(check==true) {
			check = test == g.getEdge(1, 1);
		}	
		assertEquals(check,true);
	}
	
	@Test
	void failRemoveNode() {
		graph g = new DGraph();
		
		for(int i = 1; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		int before = g.getMC();
		g.removeNode(201);
		g.removeNode(300);
		g.removeNode(0);
		g.removeNode(200);
		before+=1;
		int after = g.getMC();
		assertEquals(before,after);
	}
	
	
	@Test
	void failRemoveEdge() {
		
		graph g = new DGraph();
		
		for(int i = 1; i <= 200; i++) {
			node_data tmp = new Vertex(i,Math.random(),Math.random());
			g.addNode(tmp);
		}
		

		for(int i = 1; i < 12; i++) {
			g.connect(1,i,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(i,19,Math.random()*10);
		}
		
		for(int i = 1; i < 11; i++) {
			g.connect(17,i,Math.random()*10);
		}
		
		for(int i = 2; i < 11; i++) {
			g.connect(i,11,Math.random()*10);
		}
		
		edge_data test = g.removeEdge(1, 12);
		boolean check = test==null;
		if(check ==true)
			check = test == g.removeEdge(15, 19);
		
		if(check ==true)
			check = test == g.removeEdge(17, 19);
		
		if(check ==true)
			check = test == g.removeEdge(17, 11);
		
		if(check ==true)
			check = test == g.removeEdge(1, 15);
		
		assertEquals(check,true);
	}
}
