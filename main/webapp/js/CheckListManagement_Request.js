$(document).ready(function() {

	var optionText, array = [], newString, maxChar = 15;

	$("#activity").on("change", function() {
		if ($("#newCheck").is(":checked") == false) {
			
			var target = document.getElementById("checkListByLogined");
			var selectedCode = $("#activity option:selected").attr("id");
			var optLength = 0;
			var selectedNum = -1;
		
			if (selectedCode != null && selectedCode != "") {
				selectedCode = selectedCode.trim();
				optLength = $("input[name='"+selectedCode+"']").length;
			}

			target.options.length = 0;
			$('textarea#detail_area').text("");		
		
			if (optLength == 0) {
				var opt = document.createElement("option");
				opt.value = -1;
				opt.innerHTML = " * 해당활동에 대한 데이터가 존재하지않습니다. 관리자에게 문의하십시오.";
				target.appendChild(opt);
			} 
			else {
				var opt = document.createElement("option");
				opt.value = -1;
				opt.innerHTML = "항목을 선택해주십시오.";
				target.appendChild(opt);
				for (var i = 0; i < optLength; ++i) {
					var optList = document.createElement("option");
					optList.value = $("input[name='"+selectedCode+"']")[i].id;
					optList.innerHTML = $("input[name='"+selectedCode+"']")[i].value;
					target.appendChild(optList);
				}
			}
			
			if (optLength != 0) {
				$('#checkListByLogined').each(function() {
					$(this).find('option').each(function(i, e) {
						$(e).attr('title', $(e).text());
						optionText = $(e).text();
						newString = '';
						if (optionText.length > maxChar) {
							array = optionText.split(' ');
							$.each(array, function(ind, ele) {
								newString += ele + ' ';
								if (ind > 0 && newString.length > maxChar) {
									newString += ".."
									$(e).text(newString);
									return false;
								}
							});
						}
					});
				});
			}
		}						
	});

	$('#checkListByLogined').on("change", function() {
		var selectedValue = $('#checkListByLogined option:selected').val();
		var arrIndex_1 = -1;
		var arrIndex_2 = -1;

		if (selectedValue == -1) {
			$('textarea#detail_area').text("항목을 선택해주십시오.");
		} 
		else {
			$('textarea#detail_area').text($("input[id='"+selectedValue+"']").val());
		}
	})

	$("#newCheck").click(function() {
		if ($(this).is(":checked") == true) {
			$("#checkListByLogined option:eq(0)").text("신규 요청시 필요하지 않은 항목입니다.");			
			$("#checkListByLogined option:eq(0)").prop('selected', 'selected');
			$("textarea#detail_area").text("");
			$("#checkListByLogined").prop('disabled', true);
		} else {
			$("#checkListByLogined option:eq(0)").text("부대활동을 선택해주십시오.");
			$("#checkListByLogined").prop('disabled', false);
			$("#activity").change();
		}
	});
});