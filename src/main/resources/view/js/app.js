$(document).ready(function () {

    function addCloseButtonToListElements() {
        var myNodelist = $("LI");
        for (var i = 0; i < myNodelist.length; i++) {
            myNodelist[i].append($("<SPAN></SPAN>").text("\u00D7").addClass("close"));
        }
    }

    function addListenerToAllCloseButton(close) {
        for (var i = 0; i < close.length; i++) {
            addListenerToOneCloseButton(close[i]);
        }
    }

// todo fix
    function addListenerToOneCloseButton(el) {

        $(el).click(function (event) {

            addCheckedEventToList();

            var arrLi = $('LI').children();

            for (var i = 0; i < arrLi.length; i++) {
                if (event.target === arrLi[i]) {
                    var div = $(event.target).parent().remove(i);
                    delTodoAjax(i);
                }
            }
        })
    }

    function addCheckedEventToList() {
        var list = $('ul');

        list.children().each(function (index) {
            $(this).click(function (event) {
                if (event.target.tagName === 'LI') {
                    event.target.classList.toggle('checked');
                }
            });

        });
    }

    function newElement() {
        var inputValue = $("#myInput").val();

        if (inputValue === '') {
            alert("You must write something!");
            return;
        }
        addTodoAjax(inputValue);

    }
    function delTodoAjax(body) {

        $.ajax('/todos'.concat('?id=').concat(body), {
            method: 'GET'
        });

    }

    function addTodoAjax(body) {

        $.ajax('/add-todo', {
            method: 'POST',
            data: JSON.stringify({body: body})
        })
            .then(function success(data) {
                    if (data) {
                        console.log('Todo has been added with id ' + data.id);
                        createNewElement(data.body);
                    }
                }, function fail(data, status) {
                    console.log('Request failed.  Returned status of ' + status);
                }
            );
    }

    function createNewElement(inputValue) {
        var li = $("<li></li>").text(inputValue);

        $("#myUL").append(li);

        li.append($("<SPAN class='close'></SPAN>").text("\u00D7"));
        addListenerToOneCloseButton(li);
        $("#myInput").val("");
    }

    function takeAllTodos() {

        var ul = $("<ul id='myUL'></ul>");
        $('body').append(ul);


        $.getJSON("/todos", function (data) {
            $.each(data, function (key, val) {
                createNewElement(val.body);
            });

//                addCheckedEventToList();
        });
    }


    takeAllTodos();
    $("#addButtonId").click(newElement);
    addCloseButtonToListElements();
    var close = $(".close");
    addListenerToAllCloseButton(close);

});