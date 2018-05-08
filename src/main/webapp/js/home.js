function attachHandlers() {

    let numbers = document.getElementsByName("number");

    for (let i = 0; i < numbers.length; i++) {

        numbers[i].onclick = inputNumbers;

        }
}

function inputNumbers() {

    let field = document.getElementById('atmkeybord');

    let value = this.value;

    switch (value) {

        case 'Clean' :

            field.value = "";

            break;

        default :
            document.getElementById("atmkeybord").value += value;

            if (field.value.length === 4 || field.value.length === 9 || field.value.length === 14) {

                document.getElementById("atmkeybord").value += "-";
            }

            if (field.value.length > 19) {

                field.value = field.value.substring(0, 19);
                }

            document.getElementById('atmkeybord').focus();

            return true;

    }
}