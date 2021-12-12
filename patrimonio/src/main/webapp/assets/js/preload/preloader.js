// JavaScript Document
   	jQuery(document).ready(function(){			
	
				jQuery(".change").animate({ "width" : "25%"},1500 ); 
		 		jQuery(".change").animate({ "width" : "50%"},1500 ); 	 	
				jQuery(".change").animate({ "width" : "75%"},1500 ); 
	 			jQuery(".change").animate({ "width" : "100%"},1500,function() {
				     jQuery('#frm_preloader').submit();
    			}); 				
	 	
	});