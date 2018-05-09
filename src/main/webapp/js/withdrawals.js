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

        case 'Delete' :

            field.value = field.value.substring(0, field.value.length - 1);

            break;

        default :
            document.getElementById("atmkeybord").value += value;

            if (field.value.length > 17) {

                field.value = field.value.substring(0, 17);
            }
            document.getElementById('atmkeybord').focus();

            return true;

    }
}