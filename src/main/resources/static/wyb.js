/**
 * 
 */
	function showDiv(divId){
		document.getElementById(divId).style.display="block";
		console.log("showing the div doo doo doo dee doo doo");
	}
		function toggleDropdownByName() {
			document.getElementsByClassName("browse-by-name").classList
					.toggle("show");
		}
		function toggleDropdownByState() {
			document.getElementsByClassName("browse-by-state").classList
					.toggle("show");
		}
		function toggleParkDisplay(change) {
			let state = change.data.state;
			document.getElementsByClassName("parks-in-state").classList
					.toggle("show");
		}