function usersRefresh() {
	$.ajax({
		dataType: "json",
		url: "/user_list_ajax.action",
		success: function(result) {
			$("#users-refresh-body").empty();
			$.each(result, function() {
				$("#users-refresh-body").append(
						'<tr><td>' + this.userId + '</td><td>'
						+ this.username + '</td><td>'
						+ this.password + '</td><td>'
						+ '<button type="button" class="btn btn-default btn-xs" id="users-edit-modal-btn" users-userId="' + this.userId + '" users-username="' + this.username + '" users-password="' + this.password + '" data-toggle="modal" data-target="#users-edit-modal">S\u{1eed}a</button>'
						+ '<button type="button" class="btn btn-default btn-xs" id="users-delete-modal-btn" users-userId="' + this.userId + '" users-username="' + this.username + '" users-password="' + this.password + '" data-toggle="modal" data-target="#users-delete-modal">Xo\u{E1}</button>'
						+ '</td></tr>');
			});
		}
	});
};
$(usersRefresh);
$("#users-refresh-btn").click(usersRefresh);

$("#users-refresh-body").on("click", "#users-edit-modal-btn", function(){
	$("#users-edit-userId").val($(this).attr("users-userId"));
	$("#users-edit-username").val($(this).attr("users-username"));
	$("#users-edit-password").val($(this).attr("users-password"));
});

$("#users-refresh-body").on("click", "#users-delete-modal-btn", function(){
	$("#users-delete-userId").val($(this).attr("users-userId"));
	$("#users-delete-username").val($(this).attr("users-username"));
	$("#users-delete-password").val($(this).attr("users-password"));
});

function userAlert(selector, message) {
	$(selector).empty().append('<div class="alert alert-danger alert-dismissible"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><h4><i class="icon fa fa-info"></i>Th\u{F4}ng b\u{E1}o</h4>' + message + '</div>');
}

$("#users-create-btn").click(function() {
	$.ajax({
		type: "post",
		dataType: "json",
		url: "/user_create_ajax.action",
		data: $("#users-create-form").serialize(),
		success: function (result) {
			if (result == "NONE") {
				usersRefresh();
				$("#users-create-modal button.close").click();
				$("#users-create-form").trigger("reset");
			} else if (result == "EMPTY") {
				userAlert("#users-create-alert", "T\u{EA}n t\u{E0}i kho\u{1EA3}n v\u{E0} m\u{1EAD}t kh\u{1EA9}u kh\u{F4}ng \u{111}\u{1B0}\u{1EE3}c b\u{1ECF} tr\u{1ED1}ng.");
			} else if (result == "FAILED") {
				userAlert("#users-create-alert", "C\u{F3} l\u{1ED7}i khi t\u{1EA1}o t\u{E0}i kho\u{1EA3}n m\u{1EDB}i.");
			}
		}
	});
});

$("#users-delete-btn").click(function() {
	$.ajax({
		type: "post",
		dataType: "json",
		url: "/user_delete_ajax.action",
		data: $("#users-delete-form").serialize(),
		success: function (result) {
			if (result == "NONE") {
				usersRefresh();
				$("#users-delete-modal button.close").click();
			} else /* if (result == "FAILED") */{
				userAlert("#users-delete-alert", "C\u{F3} l\u{1ED7}i khi xo\u{E1} t\u{E0}i kho\u{1EA3}n.");
			}
		}
	});
});

$("#users-edit-btn").click(function() {
	$.ajax({
		type: "post",
		dataType: "json",
		url: "/user_edit_ajax.action",
		data: $("#users-edit-form").serialize(),
		success: function (result) {
			if (result == "NONE") {
				usersRefresh();
				$("#users-edit-modal button.close").click();
			} else if (result == "EMPTY") {
				userAlert("#users-edit-alert", "T\u{EA}n t\u{E0}i kho\u{1EA3}n v\u{E0} m\u{1EAD}t kh\u{1EA9}u kh\u{F4}ng \u{111}\u{1B0}\u{1EE3}c b\u{1ECF} tr\u{1ED1}ng.");
			} else if (result == "FAILED") {
				userAlert("#users-edit-alert", "C\u{F3} l\u{1ED7}i khi ch\u{1EC9}nh s\u{1EED}a th\u{F4}ng tin t\u{E0}i kho\u{1EA3}n.");
			}
		}
	});
});

