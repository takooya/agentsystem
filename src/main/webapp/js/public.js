
function mover(object) {

	for ( var i = 1; i < 6; i++) {
		if (i == object) {
			$("#m_" + i).attr("class", "m_li_a");
			$("#s_" + i).attr("class", "s_li_a");
		} else {
			$("#m_" + i).attr("class", "m_li");
			$("#s_" + i).attr("class", "s_li");
		}
	}
}