package com.apt.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import com.apt.model.AptVO;

public class RegPeriod {
	private int queueMaxSize = 5; //queue裡最多元素個數
	private int arrayLength;      //此陣列的長度  = queueMaxSize + 1 
	                              //因為rear == front 為判空條件
								  //利用空出一個空格與判空條件區隔，作為判滿條件
								  // ex: ( rear + 1 ) % ( arrayLength ) == front
	private int front = 0;
	private int rear = 0;
	private AptVO regQueue[];
	private String period;
	
	
	RegPeriod(  String period, int queueSize ) { //指明是哪一天的哪個小時時段的掛號紀錄
		this.period = period;
		this.queueMaxSize = queueSize;
		this.arrayLength = queueSize + 1; //要多一格 ， 為了循環queue的判空判滿條件
		this.regQueue = new AptVO[ arrayLength ];
	}
	public boolean isEmpty() {
		if ( front == rear ) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isFull() {
		if (  (rear + 1 ) % ( this.arrayLength ) == front ) {
			return true;
		} else {
			return false;
		}
	}
	
	public int calculateElements() {
		return ( rear - front + arrayLength ) % arrayLength ;
	}
	public boolean enQueue( AptVO aptVO ) {
		if ( isFull() ) {
			return false;
		}
		regQueue[ rear ] = aptVO;
		rear = ( rear + 1 ) % arrayLength;
		return true;
	}
	public boolean deQueue() {
		if ( isEmpty() ) {
			return false;
		} 
		
		regQueue[ front ] = null;
		front = ( front + 1 ) % arrayLength;
		return true;
	}
	
	public AptVO[] getRegQueue() {
		return regQueue;
	}

	public void setRegQueue(AptVO[] regQueue) {
		this.regQueue = regQueue;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	
	public int getQueueSize() {
		return queueMaxSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueMaxSize = queueSize;
	}
	public int getFront() {
		return front;
	}
	public void setFront(int front) {
		this.front = front;
	}
	public int getRear() {
		return rear;
	}
	public void setRear(int rear) {
		this.rear = rear;
	}
	
	public void swap( int a, int b ) {
		int aPosition = ( this.front + ( a - 1 ) ) % arrayLength; 
		int bPosition = ( this.front + ( b - 1 ) ) % arrayLength;
		AptVO temp = regQueue[ aPosition ]; 
		regQueue[ aPosition ] = regQueue[ bPosition ];
		regQueue[ bPosition ] = temp;
	}
	
	public String[] myToString() {
		String[] result = new String[ calculateElements() ];
		int front = this.front;
		int rear = this.rear;
		int i = 0;
		while( front != rear ) {
			result[ i ] = regQueue[ front ].getAptNo();
			front = ( front + 1 ) % arrayLength;
			i++;
		}
		return result;
	}
	
//	public int whichRegIsEmptyForOnline( int row ){ //幫找出掛號空位, 如果沒有則返回0
//		int i = row; //row
//		int j = 1; //column
//		int count = 0;
//		for ( ; j <= 3; j++ ) {
//			if ( reg[i][j] == null ) {
//				count++;
//			}
//		}
//		if ( count != 0 ) {
//			t1 : while ( true ) {   //隨機找出掛號空位
//				j = ( int )( Math.random() * 3 ) + 1;
//				if ( reg[i][j] == null ) {
//					return j; 
//				}
//			}
//		} else {  
//			return count;  
//		}
//		
//	}
	
	public static void main(String[] args) {
//		AptVO regQueue[] = new AptVO[ 5 ];
//		String period = "1011";	
//		Date date = new Date( new GregorianCalendar().getTimeInMillis() );
//		RegPeriod regDay = new RegPeriod(regQueue, period, date);
//		regDay.getRegQueue()[1] = new AptVO();
		//AptVO regQueue[] = new AptVO[ 5 ];
		String period = "1011";	
		Date date = new Date( new GregorianCalendar().getTimeInMillis() );
		//System.out.println( Integer.parseInt( "09" ));
		RegPeriod regPeriod = new RegPeriod("1011", 5 );
		AptVO aptVO = new AptVO();
		aptVO.setAptDate(date);
		aptVO.setAptNo("7001");
		regPeriod.enQueue(aptVO );
		System.out.println( regPeriod.myToString());
		ArrayList ary = new ArrayList();
		ary.add("fq");
		
		/*int i = 1;
		for ( Object o : ary ) {
			if ( i == ary.size() - 1  ) {
				
			} else {
				
			}
			i++;
		}*/
		
		//System.out.println(ary[0]);
	}
	
}
