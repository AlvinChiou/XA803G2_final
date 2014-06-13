package com.shift.model;


import java.sql.Date;
import java.util.List;

public interface ShiftDAO_interface {
        public int insert(ShiftVO shiftVO);
        public int update(ShiftVO shiftVO);
        public int delete(String shiftNo);
        public ShiftVO findByPrimaryKey(String shiftNo);
        public List<ShiftVO> getAll();
        /**自己新增的*/
        public String[] findShiftPeriodByDate( Date shiftDate );
        public List<ShiftVO> getFuture20All();
        public String getShiftNo( Date shiftDate, String shiftPeriod );
}

