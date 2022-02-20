let curId;

function openEditPage(id) {
    document.getElementById("edit-dialog").classList.add("visible");
    $("#edit-category").val(document.getElementById('category-'+id).innerHTML)
    $("#edit-description").attr('value', document.getElementById("description-"+id).innerHTML);
    $("#edit-description").val(document.getElementById("description-"+id).innerHTML);
    $("#edit-choiceA").attr('value', document.getElementById("choiceA-"+id).innerHTML);
    $("#edit-choiceA").val(document.getElementById("choiceA-"+id).innerHTML);
    $("#edit-choiceB").attr('value', document.getElementById("choiceB-"+id).innerHTML);
    $("#edit-choiceB").val(document.getElementById("choiceB-"+id).innerHTML);
    $("#edit-choiceC").attr('value', document.getElementById("choiceC-"+id).innerHTML);
    $("#edit-choiceC").val(document.getElementById("choiceC-"+id).innerHTML);
    $("#edit-choiceD").attr('value', document.getElementById("choiceD-"+id).innerHTML);
    $("#edit-choiceD").val(document.getElementById("choiceD-"+id).innerHTML);
    $("#edit-answer").val(document.getElementById('answer-'+id).innerHTML)
    curId = id;
}

function editQuestion() {
    $.ajax({
        url: "/admin/edit-choice-question",
        type: "POST",
        data: "id=" + curId +
            "&category=" + $("#edit-category").val() +
            "&description=" + document.getElementById('edit-description').getAttribute("value") +
            "&choiceA=" + document.getElementById('edit-choiceA').getAttribute("value") +
            "&choiceB=" + document.getElementById('edit-choiceB').getAttribute("value") +
            "&choiceC=" + document.getElementById('edit-choiceC').getAttribute("value") +
            "&choiceD=" + document.getElementById('edit-choiceD').getAttribute("value") +
            "&answer=" + $("#edit-answer").val(),
        dataType: "json",
        success: updateEdit,
        error: updateError
    });
}

function updateEdit(response) {
    if (response.hasOwnProperty('error')) {
        displayError(response.error)
    } else {
        $("#category-"+response.id).empty()
        $("#category-"+response.id).append(response.category);
        $("#description-"+response.id).empty()
        $("#description-"+response.id).append(response.description);
        $("#choiceA-"+response.id).empty()
        $("#choiceA-"+response.id).append(response.choiceA);
        $("#choiceB-"+response.id).empty()
        $("#choiceB-"+response.id).append(response.choiceB);
        $("#choiceC-"+response.id).empty()
        $("#choiceC-"+response.id).append(response.choiceC);
        $("#choiceD-"+response.id).empty()
        $("#choiceD-"+response.id).append(response.choiceD);
        $("#answer-"+response.id).empty()
        $("#answer-"+response.id).append(response.answer);
            }
    document.getElementById("edit-dialog").classList.remove("visible")
}