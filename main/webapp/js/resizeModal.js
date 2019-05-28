$(document).ready(function(){
	
	var delta = 300;
	var timer = null;

	$( window ).on( 'resize', function( ) {
	    clearTimeout( timer );
	    timer = setTimeout( resizeDone, delta );
	} );

	function resizeDone( ) {
		if (parent.window.innerWidth <= 620) {
			if ($("#modal-table-head").children().children().length == 1) {
				var clone_title = $("#modal-table-head").children().children().children().eq(0).clone();
				var clone_inner = $("#modal-table-head").children().children().children().eq(1).clone();
				$("#modal-table-head").children().children().children().eq(0).remove();
				$("#modal-table-head").children().children().children().eq(0).remove();
				$("#modal-table-head").children().children().before("<tr></tr>");
				$("#modal-table-head").children().children().eq(0).append(clone_title);
				$("#modal-table-head").children().children().eq(0).append(clone_inner);
				$("#modal-table-head").children().children().eq(0).css("text-align", "center");
				$("#modal-table-head").children().children().eq(1).css("text-align", "center");
				//$("#modal-table-head").children().children().eq(1).css("line-height", "1.4");
				
				$("#td_head").css("width", "101px");
				$("#td_colspan1").attr("colspan", 2);
				$("#td_colspan2").attr("colspan", 2);
			}		
		}
		else {
			if ($("#modal-table-head").children().children().length != 1) {
				var clone_title = $("#modal-table-head").children().children().eq(0).children().eq(0).clone();
				var clone_inner = $("#modal-table-head").children().children().eq(0).children().eq(1).clone();
				$("#modal-table-head").children().children().eq(0).remove();
				$("#modal-table-head").children().children().children().eq(0).before(clone_inner);
				$("#modal-table-head").children().children().children().eq(0).before(clone_title);
				$("#modal-table-head").children().children().eq(0).css("text-align", "unset");
				//$("#modal-table-head").children().children().eq(0).css("line-height", "0px");
				
				$("#td_head").css("width", "unset");
				$("#td_colspan1").attr("colspan", 1);
				$("#td_colspan2").attr("colspan", 1);
			}		
		}
	}
})