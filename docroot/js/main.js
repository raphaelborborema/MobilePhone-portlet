$(document).ready(function() {
	
	$(':input').on("drop", function() {
		return false;
	});
	$(document).on("focusin", ".JSvalorMoeda", function() { 
		
		$('.JSvalorMoeda').maskMoney({allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
	});
	
	
	
}); 