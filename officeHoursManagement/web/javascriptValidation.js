 function ajaxDiplayName()
            {
                
                var displayName = document.getElementById("displayName").value;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET", "AjaxProcessDisplayName?displayName=" + displayName, true);
                xmlhttp.send();

                   
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                      document.getElementById("error1").hidden = false;
                     
                     /*
                     if(xmlhttp.responseText === "Done successfully")
                     {
                         
                     }
                     */
                     
                      document.getElementById("error1").innerHTML = xmlhttp.responseText;
                        
                    }

                };

            }
 function ajaxSlot()
            {
                
                var fromTime = document.getElementById("fromTime").value;
                var toTime = document.getElementById("toTime").value;
                var date = document.getElementById("date").value;
                alert(fromTime);
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET", "AjaxProcessSlot?fromTime=" + fromTime +"toTime="+toTime+"date="+date, true);
                xmlhttp.send();

                   
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                      document.getElementById("error3").hidden = false;
                     
                     /*
                     if(xmlhttp.responseText === "Done successfully")
                     {
                         
                     }
                     */
                     
                      document.getElementById("error3").innerHTML = xmlhttp.responseText;
                        
                    }

                };

            }
 
 function ajaxEmail()
 {
                var email = document.getElementById("email").value;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET", "AjaxProcessEmail?email=" + email, true);
                xmlhttp.send();

                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                     
                       /* document.getElementById("error2").hidden = false;*/
                        document.getElementById("error2").innerHTML = xmlhttp.responseText;
                    }

                };

   
 }
 
 
    
    function required()
{
var empt = document.form1.userName.value;
var empt1 = document.form1.displayName.value;
var empt2 = document.form1.email.value;
var empt3 = document.form1.department.value;
var empt4 = document.form1.subject.value;
if (empt == "" ||empt1 == "" ||empt2 == "" ||empt3 == "" ||empt4 == "" )
{
alert("There is an empty Value!!!");
return false;
}
return true;

}
