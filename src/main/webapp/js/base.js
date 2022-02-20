function updateError(xhr, status, error) {
    displayError('Status=' + xhr.status + ' (' + error + ')')
}

function displayError(message) {
    let errorElement = document.getElementById("error")
    errorElement.innerHTML = message
}