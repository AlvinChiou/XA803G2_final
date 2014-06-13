package com.apt.controller;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServlet;

import com.shift.model.ShiftDAO;
import com.shift.model.ShiftJDBCDAO;
import com.shift.model.ShiftVO;

public class RegDay {
	private static final int QUEUE_SIZE = 5;
	private Date date;
	private RegPeriod regPeriods[] = new RegPeriod[7] ;

	public RegDay(Date date) {
		this.date = date;
		/**�H�U�O�b�ʺA�إ߸Ӥu�@�Ѫ��u�@�ɬq(�W�� �U��)*/
		ShiftDAO dao = new ShiftDAO();
		String[] s = dao.findShiftPeriodByDate( date );
		for ( int i = 0; i < s.length; i++ ) {
			if ( "�W��".equals( s[i] ) ) {
				this.regPeriods[ 0 ] = new RegPeriod( "0910", QUEUE_SIZE );
				this.regPeriods[ 1 ] = new RegPeriod( "1011", QUEUE_SIZE );	
				this.regPeriods[ 2 ] = new RegPeriod( "1112", QUEUE_SIZE );
			}
			if  ( "�U��".equals( s[i]) )  {
				this.regPeriods[ 3 ] = new RegPeriod( "1314", QUEUE_SIZE );
				this.regPeriods[ 4 ] = new RegPeriod( "1415", QUEUE_SIZE );	
				this.regPeriods[ 5 ] = new RegPeriod( "1516", QUEUE_SIZE );
				this.regPeriods[ 6 ] = new RegPeriod( "1617", QUEUE_SIZE );
			}
		}
	}
	
	public int returnPeriodInIndex( String period ){
		switch( period ) {
			case "0910" :  {
				return 0;
			}
			case "1011" :  {
				return 1;
			}
			case "1112" :  {
				return 2;
			}
			case "1314" :  {
				return 3;
			}
			case "1415" :  {
				return 4;
			}
			case "1516" :  {
				return 5;
			}
			case "1617" :  {
				return 6;
			}
			default : {
				return -1;
			}
		}
	}

	/*public RegPeriod[] getRegPeriodsInOneDay() {
		RegPeriod[]  regPeriods =  new RegPeriod[7];
		ShiftDAO shiftDao = new ShiftDAO();
		List<ShiftVO> shiftList = shiftDao.getAll();
		//�Q��treeSet�ӱƧ���o���Z�����A�BSet�|�N�@�˪�����X�֬��@��!!
		Set<Date> treeSet = new TreeSet<Date>();
		for ( ShiftVO shiftVO : shiftList ) {
			Date date = shiftVO.getShiftDate();
			treeSet.add(date);
		}
		//�A�NtreeSet�̪�Date���� ��J�@��Date�}�Cdates2
		Date[] dates = new Date[0];
		System.out.println( "treeSet" + treeSet) ; 
		Date[] dates2 = treeSet.toArray(dates);
		System.out.println( dates2 );
		for ( int i = 0; i < dates2.length; i ++ ) {
			System.out.println( dates2[i]);
		}
		
		
		
//		for ( ShiftVO shiftVO : shiftList ) {
//			String amOrPm = shiftVO.getShift_Period();
//			if ( "�W��".equals( amOrPm ) ) {
//				regPeriods[ 0 ] = new RegPeriod( "0910", QUEUE_SIZE );
//				regPeriods[ 1 ] = new RegPeriod( "1011", QUEUE_SIZE );	
//				regPeriods[ 2 ]	= new RegPeriod( "1112", QUEUE_SIZE );
//			}
//			if ( "�U��".equals( amOrPm ) ) {
//				regPeriods[ 3 ] = new RegPeriod( "1314", QUEUE_SIZE );
//				regPeriods[ 4 ] = new RegPeriod( "1415", QUEUE_SIZE );	
//				regPeriods[ 5 ]	= new RegPeriod( "1516", QUEUE_SIZE );
//				regPeriods[ 6 ]	= new RegPeriod( "1617", QUEUE_SIZE );
//			}
//		}
//		for ( int i = 0; i < 7; i++ ) {
//			if ( "�W��".equals(anObject) && "�U��".equals(anObject) ) {
//				
//			} else if ( "�W��".equals("") &&  )
//		}
		return regPeriods;
		
	}*/

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RegPeriod[] getRegPeriods() {
		return regPeriods;
	}

	public void setRegPeriods(RegPeriod[] regPeriods) {
		this.regPeriods = regPeriods;
	}


	
	public static void main(String[] args) {
		Set<Date> treeSet = new TreeSet<Date>();
		Set<Date> hashSet = new HashSet<Date>();
		ShiftJDBCDAO shiftDao = new ShiftJDBCDAO();
		List<ShiftVO> shiftList = shiftDao.getAll();
		for ( ShiftVO shiftVO : shiftList ) {
			Date date = shiftVO.getShiftDate();
			treeSet.add(date);
			//hashSet.add(date);
		}
		//hashSet���|���ƧǡAtreeSet�~�|!
		Date[] dates = new Date[0];
		System.out.println( "treeSet" + treeSet) ; 
		Date[] dates2 = treeSet.toArray(dates);
		System.out.println( dates2 );

		for ( int i = 0; i < dates2.length; i ++ ) {
			System.out.println( dates2[i]);
		}
		
		//	System.out.println( hashSet.toString());
		
		System.out.println("----���-----");
		System.out.println( dates2[15]);
		System.out.println( (int)( dates2[15].getTime() - new java.util.Date().getTime() ) / AptServlet.ONE_DAY_IN_MILLISECONDS );
		//System.out.println(dates2[15].compareTo(new java.util.Date()) );
	}

}
