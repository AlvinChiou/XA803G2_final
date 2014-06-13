package com.utilities;

public class SubPages {
	private int each_disNums;// 每頁顯示的條目數 
    private int nums;// 總條目數 
    private int current_page;// 目前所在頁數
    private int sub_pages;// 每次顯示的頁數
    private int pageNums;// 總頁數 總數/每次顯示的頁數
 
    // private java.util.ArrayList<Integer> page_array;// 用來構造分頁的數組
 
    private String subPage_link;// 每個分頁的鏈接
 
    // private int subPage_type;// 顯示分頁的類型
 
    /*
     * 當subPage_type=1的時候為普通分頁模式 example： 共4523條記錄,每頁顯示10條,當前第1/453頁 [首頁] [上頁]
     * [下頁] [尾頁] 當subPage_type=2的時候為經典分頁樣式 example： 當前第1/453頁 [首頁] [上頁] 1 2 3 4
     * 5 6 7 8 9 10 [下頁] [尾頁]
     */
 
    public SubPages(int each_disNums, int nums, int current_page,
            int sub_pages, String subPage_link) {
        this.each_disNums = each_disNums;
        this.nums = nums;
        if (current_page < 1) {
            this.current_page = 1;
        } else {
            this.current_page = current_page;
        }
        this.sub_pages = sub_pages;
        this.pageNums = (int) java.lang.Math.ceil(nums / each_disNums);
        this.subPage_link = subPage_link;
        // this.show_SubPages(subPage_type);
        // echo pageNums."--".sub_pages;
 
    }
 
    /*
     * show_SubPages函數用在構造函數里面。而且用來判斷顯示什麼樣子的分頁type
     */
    // private String show_SubPages(int subPage_type){
    // if(subPage_type == 1){
    // return this.subPageCss1();
    // }else if (subPage_type == 2){
    // return this.subPageCss2();
    // }
    // }
    /*
     * 用來給建立分頁的數組初始化的函數。1,2,3,4,5,6.....
     */
    public java.util.ArrayList<Integer> initArray() {
        java.util.ArrayList<Integer> page_array = new java.util.ArrayList<Integer>();
        for (int i = 0; i < this.sub_pages; i++) {
            page_array.add(i, i);
            ;
        }
        return page_array;
    }
 
    /*
     * construct_num_Page該函數使用來構造顯示的條目 即使：[1][2][3][4][5][6][7][8][9][10]
     */
    public java.util.ArrayList<Integer> construct_num_Page() {
        java.util.ArrayList<Integer> current_array = new java.util.ArrayList<Integer>();
        if (this.pageNums < this.sub_pages) {
            // current_array=array();
            for (int i = 0; i < this.pageNums; i++) {
                current_array.add(i, i + 1);
            }
        } else {
            current_array = this.initArray();
            if (this.current_page <= 3) {
                for (int i = 0; i < current_array.size(); i++) {
                    current_array.set(i, i + 1);
                }
            } else if (this.current_page <= this.pageNums
                    && this.current_page > this.pageNums - this.sub_pages + 1) {
                for (int i = 0; i < current_array.size(); i++) {
                    current_array.set(i, (this.pageNums) - (this.sub_pages) + 1
                            + i);
                }
            } else {
                for (int i = 0; i < current_array.size(); i++) {
                    current_array.set(i, this.current_page - 2 + i);
                }
            }
        }
        for (int i = 0; i < current_array.size(); i++)
            System.out.print(current_array.get(i) + " ");
        System.out.println();
        return current_array;
    }
 
    /*
     * 構造普通模式的分頁 共4523條記錄,每頁顯示10條,當前第1/453頁 [首頁] [上頁] [下頁] [尾頁]
     */
    public String subPageCss1() {
        String subPageCss1Str = "";
        subPageCss1Str += "共" + this.nums + "筆記錄，";
        subPageCss1Str += "每頁顯示" + this.each_disNums + "筆，";
        subPageCss1Str += "目前第" + this.current_page + "/" + this.pageNums
                + "頁 ";
        if (this.current_page > 1) {
            String firstPageUrl = this.subPage_link + "1";
            String prewPageUrl = this.subPage_link + (this.current_page - 1);
            subPageCss1Str += "[<a href='" + firstPageUrl + "'>首頁</a>] ";
            subPageCss1Str += "[<a href='" + prewPageUrl + "'>上一頁</a>] ";
        } else {
            subPageCss1Str += "[首頁] ";
            subPageCss1Str += "[上一頁] ";
        }
 
        if (this.current_page < this.pageNums) {
            String lastPageUrl = this.subPage_link + this.pageNums;
            String nextPageUrl = this.subPage_link + (this.current_page + 1);
            subPageCss1Str += " [<a href='" + nextPageUrl + "'>下一頁</a>] ";
            subPageCss1Str += "[<a href='" + lastPageUrl + "'>尾頁</a>] ";
        } else {
            subPageCss1Str += "[下一頁] ";
            subPageCss1Str += "[尾頁] ";
        }
 
        return subPageCss1Str;
 
    }
 
    /*
     * 構造經典模式的分頁 當前第1/453頁 [首頁] [上頁] 1 2 3 4 5 6 7 8 9 10 [下頁] [尾頁]
     */
    public String subPageCss2() {
        String subPageCss2Str = "";
        subPageCss2Str += "第" + current_page + "/" + pageNums + "頁 ";
 
        if (current_page > 1) {
            String firstPageUrl = subPage_link + "1";
            String prewPageUrl = subPage_link + (current_page - 1);
            subPageCss2Str += "[<a href='" + firstPageUrl + "'>首頁</a>] ";
            subPageCss2Str += "[<a href='" + prewPageUrl + "'>上一頁</a>] ";
        } else {
            subPageCss2Str += "[首頁] ";
            subPageCss2Str += "[上一頁] ";
        }
 
        java.util.ArrayList<Integer> a = construct_num_Page();
        int s;
        for (int i = 0; i < a.size(); i++) {
            s = a.get(i);
            if (s == current_page) {
                subPageCss2Str += "[<span style='color:red;font-weight:bold;'>"
                        + s + "</span>]";
            } else {
                String url = subPage_link + s;
                subPageCss2Str += "[<a href='" + url + "'>" + s + "</a>]";
            }
        }
 
        if (current_page < pageNums) {
            String lastPageUrl = subPage_link + pageNums;
            String nextPageUrl = subPage_link + (current_page + 1);
            subPageCss2Str += " [<a href='" + nextPageUrl + "'>下一頁</a>] ";
            subPageCss2Str += "[<a href='" + lastPageUrl + "'>最末頁</a>] ";
        } else {
            subPageCss2Str += "[下一頁] ";
            subPageCss2Str += "[最末頁] ";
        }
        return subPageCss2Str;
    }
 
    public static void main(String args[]) {
    	com.utilities.SubPages pages = new com.utilities.SubPages(20, 1031, 13, 10,
                "index.jsp?p=");
        System.out.println(pages.subPageCss2());
    }
    /*JSP
     * <%
		//if(!$pageCurrent) $pageCurrent=1;
		   	int rowNumber=list.size();
		 	com.utilities.SubPages  pages = new com.utilities.SubPages(10, rowNumber, 10, 10, "index.jsp?p=");
			out.print("樣式一："+pages.subPageCss1());
			out.print("</br>");
			out.print("樣式二："+pages.subPageCss2());
	   %>
     * 
     * */
}
