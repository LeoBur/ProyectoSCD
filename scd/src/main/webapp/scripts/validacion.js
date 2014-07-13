$(document).ready(function() {
 
 // attach datepicker to the input field
 // an interactive calendar appears as soon as you focus on the field
 // for more information read http://jqueryui.com/demos/datepicker/
 $("#myDate").datepicker();
  
 // modify default settings for validation
 jQuery.validator.setDefaults({
  // where to display the error relative to the element
  errorPlacement: function(error, element) {
      error.appendTo(element.parent().find('div.myErrors'));
     }
 });
  
 // custom validation method to make sure the date is in mm/dd/yyyy format 
 jQuery.validator.addMethod(
     "usaDate",
     function(value, element) {
      console.log(value);
      var date = getDateFromFormat(value,'MM/dd/yyyy');
      console.log(date);
      if (date == 0) { 
       return false; 
      }
      return true;
     },
     "Please enter a date in the format mm/dd/yyyy"
 );
  
 
  
 $("#samplecode").validate({
   rules: {
     
    // mandatory entry
    myInputBox: "required",
     
    // mandatory entry and valid email address
    myEmail: {
        required: true,
        email: true
      },
       
    // mandatory entry and valid URL  
       myURL: {
        required: true,
        url: true
      }, 
       
    // madatory date entry and valid date format  
    myDate : {
     required: true,
              usaDate : true
            },
             
          // mandatory radio button selection  
     myRadioButton: "required",
      
     // mandatory checkbox selection  
    myCheckbox: "required",
     
    // mandatory drop down selection  
    mySelect: "required",
     
    //conditional validation of the textarea based on the radion button selection
    convicted: "required",
    explain: {
     required: function(element) {
      return $("input:radio[name=convicted]:checked").val() == 'Y';
       }
    }
  },
   
  //custom error messages
        messages: {
         myRadioButton:{
          required: "Please choose a Color."
          },
         myCheckbox:{
             required: "Please select the Fruits that you love."
             },
         myInputBox: {
        	 required: "Introduci algo para ver si anda locooooo...."
         },
        },
         
        // on page submit 
        submitHandler: function(){
          alert('Form validation was a success, please proceed!');
        }
 
 });
  
  
});