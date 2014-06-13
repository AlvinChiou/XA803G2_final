package com.utilities;

public class SubPages {
	private int each_disNums;// �C����ܪ����ؼ� 
    private int nums;// �`���ؼ� 
    private int current_page;// �ثe�Ҧb����
    private int sub_pages;// �C����ܪ�����
    private int pageNums;// �`���� �`��/�C����ܪ�����
 
    // private java.util.ArrayList<Integer> page_array;// �ΨӺc�y�������Ʋ�
 
    private String subPage_link;// �C�Ӥ������챵
 
    // private int subPage_type;// ��ܤ���������
 
    /*
     * ��subPage_type=1���ɭԬ����q�����Ҧ� example�G �@4523���O��,�C�����10��,��e��1/453�� [����] [�W��]
     * [�U��] [����] ��subPage_type=2���ɭԬ��g������˦� example�G ��e��1/453�� [����] [�W��] 1 2 3 4
     * 5 6 7 8 9 10 [�U��] [����]
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
     * show_SubPages��ƥΦb�c�y��ƨ����C�ӥB�ΨӧP�_��ܤ���ˤl������type
     */
    // private String show_SubPages(int subPage_type){
    // if(subPage_type == 1){
    // return this.subPageCss1();
    // }else if (subPage_type == 2){
    // return this.subPageCss2();
    // }
    // }
    /*
     * �Ψӵ��إߤ������Ʋժ�l�ƪ���ơC1,2,3,4,5,6.....
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
     * construct_num_Page�Ө�ƨϥΨӺc�y��ܪ����� �Y�ϡG[1][2][3][4][5][6][7][8][9][10]
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
     * �c�y���q�Ҧ������� �@4523���O��,�C�����10��,��e��1/453�� [����] [�W��] [�U��] [����]
     */
    public String subPageCss1() {
        String subPageCss1Str = "";
        subPageCss1Str += "�@" + this.nums + "���O���A";
        subPageCss1Str += "�C�����" + this.each_disNums + "���A";
        subPageCss1Str += "�ثe��" + this.current_page + "/" + this.pageNums
                + "�� ";
        if (this.current_page > 1) {
            String firstPageUrl = this.subPage_link + "1";
            String prewPageUrl = this.subPage_link + (this.current_page - 1);
            subPageCss1Str += "[<a href='" + firstPageUrl + "'>����</a>] ";
            subPageCss1Str += "[<a href='" + prewPageUrl + "'>�W�@��</a>] ";
        } else {
            subPageCss1Str += "[����] ";
            subPageCss1Str += "[�W�@��] ";
        }
 
        if (this.current_page < this.pageNums) {
            String lastPageUrl = this.subPage_link + this.pageNums;
            String nextPageUrl = this.subPage_link + (this.current_page + 1);
            subPageCss1Str += " [<a href='" + nextPageUrl + "'>�U�@��</a>] ";
            subPageCss1Str += "[<a href='" + lastPageUrl + "'>����</a>] ";
        } else {
            subPageCss1Str += "[�U�@��] ";
            subPageCss1Str += "[����] ";
        }
 
        return subPageCss1Str;
 
    }
 
    /*
     * �c�y�g��Ҧ������� ��e��1/453�� [����] [�W��] 1 2 3 4 5 6 7 8 9 10 [�U��] [����]
     */
    public String subPageCss2() {
        String subPageCss2Str = "";
        subPageCss2Str += "��" + current_page + "/" + pageNums + "�� ";
 
        if (current_page > 1) {
            String firstPageUrl = subPage_link + "1";
            String prewPageUrl = subPage_link + (current_page - 1);
            subPageCss2Str += "[<a href='" + firstPageUrl + "'>����</a>] ";
            subPageCss2Str += "[<a href='" + prewPageUrl + "'>�W�@��</a>] ";
        } else {
            subPageCss2Str += "[����] ";
            subPageCss2Str += "[�W�@��] ";
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
            subPageCss2Str += " [<a href='" + nextPageUrl + "'>�U�@��</a>] ";
            subPageCss2Str += "[<a href='" + lastPageUrl + "'>�̥���</a>] ";
        } else {
            subPageCss2Str += "[�U�@��] ";
            subPageCss2Str += "[�̥���] ";
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
			out.print("�˦��@�G"+pages.subPageCss1());
			out.print("</br>");
			out.print("�˦��G�G"+pages.subPageCss2());
	   %>
     * 
     * */
}
