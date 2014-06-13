  
function Check_IDValue3(p_id)
{
	var score_i=0,score_c=0;
	var l_str='abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_';

	
	for (var i = 0;  i < p_id.length;  i++)
  	{
    		if (l_str.indexOf(p_id.charAt(i),0)>=0) 
    		{
    			score_c+=1;
    		}      
    
	}
	
	if (score_c == p_id.length)
		return true;
	return false;	
}


function Check_IDValue(p_id)
{
	var score_i=0,score_c=0;
	var l_str='abcdefghijklmnopqrstuvwxyz0123456789_';

	
	for (var i = 0;  i < p_id.length;  i++)
  	{
    		if (l_str.indexOf(p_id.charAt(i),0)>=0) 
    		{
    			score_c+=1;
    		}      
    
	}
	
	if (score_c == p_id.length)
		return true;
	return false;	
}

//檢查第一個字母是否為英文字母
function Check_IDValue2(p_id)
{
	var score_i=0,score_c=0;
	var l_str='abcdefghijklmnopqrstuvwxyz';
	
    		if (l_str.indexOf(p_id.charAt(0),0)>=0) 
    		{
    			score_c+=1;
    		}      
    
	if (score_c != 0 )
		return true;
	return false;	
}

//檢查身份證字號
function Check_CIDValue(p_cid)
{
	 p_cid=p_cid.toUpperCase();
	 
	 var l_idstr='          ABCDEFGHJKLMNPQRSTUVXYWZIO' ;
       
     var n=0;
    
     var tot1 = Math.floor((l_idstr.indexOf(p_cid.charAt(0),0)+0)/10) + ((parseFloat(l_idstr.indexOf(p_cid.charAt(0),0)+0)%10) * 9);
     var tot2 = 0;
     for(i=1;i<p_cid.length-1;i++)
     {
		    tot2 = tot2 + p_cid.charAt(i)*(9-i);
     }
     var tot3 = parseFloat(p_cid.charAt(9));
     var tot4 = tot1 + tot2 + tot3;
     if((tot4 % 10)!=0)
        return false;
  return true;	
}

 //檢查生日格式開頭
function Check_NumValue(p_str,p_value)
{

  var checkOK = p_value;
  var checkStr = p_str;
  var allValid = true;
  var decPoints = 0;
  
  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
  }
  if (!allValid)  return (false);
  return (true);
}

function IsDate(p_Year,p_Month,p_Day)
{
	var l_LegalDay = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	var Current_Date = new Date();
	var l_Month = Current_Date.getMonth();
	var l_Year = Current_Date.getYear();
	var l_Day=Current_Date.getDate();
	
	if (!Check_NumValue(p_Year,"0123456789")) return false;
	if (!Check_NumValue(p_Month,"0123456789")) return false;
	if (!Check_NumValue(p_Day,"0123456789")) return false;
		
	if ((p_Year % 400 == 0) || ((p_Year % 4 == 0) && (p_Year % 100 != 0))) l_LegalDay[1] = 29;	
	
	if (p_Month > 12 || p_Month < 1 )  return false;
	
	if (p_Day > l_LegalDay[p_Month-1]  || p_Day < 1 )  return false;
	
	//if (p_Year+"."+p_Month+"."+p_Day > l_Year+"."+l_Month+"."+l_Day) return false;
		
	return true;
}

function chkDate(datestr) { 
    var year, month, day; 
    var pattern = /^\d{4}\/\d{1,2}\/\d{1,2}$/; 
    var tmpary  = new Array() 
    if (!pattern.test(datestr))  return false; 

    tmpary = datestr.split("/"); 
    year  = tmpary[0]; 
    month = tmpary[1]; 
    day   = tmpary[2]; 

    if (month<1 || month>12 || day>31 || day<1)  return false; 

    if (month == 2 && day > 28) { 
        if ((year%4==0 && year%100!=0) || (year%400==0)) { // 為閏年 
            if (day > 29) return false; 
        } 
        else {  // 非閏年 
            return false; 
        } 
    } 
    if (day>30 && ((month % 2) == Math.floor(month/8))) return false; 

    return true; 
} 

//檢查生日格式結尾


//去除字串左邊的空白虛格
function ltrim(instr){
  return instr.replace(/^[\s]*/gi,"");
}

//去除字串右邊的空白虛格
function rtrim(instr){
  return instr.replace(/[\s]*$/gi,"");
}

//去除字串前後的空白虛格
function trim(instr){
  instr = ltrim(instr);
  instr = rtrim(instr);
  return instr;
}