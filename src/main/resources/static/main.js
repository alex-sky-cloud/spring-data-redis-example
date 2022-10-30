$(document).ready(function () {

    const keyInput = $('#keyInput'),
        valueInput = $('#valueInput');

    refreshTable();

    $('#addForm').on('submit', function (event) {
        const data = {
            key: keyInput.val(),
            value: valueInput.val()
        };

        addItem(data);

    });
    /* Focus keyboard input into key input; ready to start typing */
    keyInput.focus();
});

function addItem(data) {
    /*
             * Persist the new key-value pair.
             * Clear the inputs.
             * Set keyboard focus to key input: ready to start typing.
             */
    $.post('api/keys', data, function() {
        refreshTable();
        keyInput.val('');
        valueInput.val('');
        keyInput.focus();
    });
    /* Prevent HTTP form submit */
    event.preventDefault();
}

    /**
     * Create HTML table row.
     *
     * \param text (str) HTML code to be placed inside the row.
     */
    function tr(text) {
        return '<tr>' + text + '</tr>';
    }

    /**
     * Create HTML table cell element.
     *
     * \param text (str) The text to be placed inside the cell.
     */
    function td(text) {
        return '<td>' + text + '</td>';
    }

    /**
     * Edit value: load key and value into inputs.
     */
    function editButton(key) {
        var format = '<button '
            + 'class="btn btn-default" '
            + 'onclick="editKey(\'{key}\')" '
            + '>Edit</button>';
        return format.replace(/{key}/g, key);
    }

    /**
     * Create HTML button with which a key-value pair is deleted from persistence.
     *
     * \param key (str) that'll be deleted when the created button is clicked.
     */
    function deleteButton(key) {
        var format = '<button '
            + 'class="btn btn-default" '
            + 'data-key="{key}" '
            + 'onclick="deleteKey(\'{key}\')" '
            + '>Delete</button>'
        return format.replace(/{key}/g, key);
    }

    /**
     * Create HTML table row element.
     *
     * \param key (str) text into the key cell.
     * \param value (str) text into the value cell.
     */
    function row(key, value) {
        return $(
            tr(
                td(key) +
                td(value) +
                td(editButton(key)) +
                td(deleteButton(key))));
    }

    /**
     * Clear and reload the values in data table.
     */
    function refreshTable() {
        $.ajax({
            url: '/api/values',         /* Куда пойдет запрос */
            method: 'get',             /* Метод передачи (post или get) */
            dataType: 'json',          /* Тип данных в ответе (xml, json, script, html). */
            data: {text: 'Текст'},     /* Параметры передаваемые в запросе. */
            success: function (data) {

                /* функция которая будет выполнена после успешного запроса.  */
                var attr,
                    mainTable = $('#mainTable tbody');
                mainTable.empty();
                for (attr in data) {
                    if (data.hasOwnProperty(attr)) {
                        mainTable.append(row(attr, data[attr]));
                    }
                }


            }
        });


        $.get('api/values', function (data) {
            var attr,
                mainTable = $('#mainTable tbody');
            mainTable.empty();
            for (attr in data) {
                if (data.hasOwnProperty(attr)) {
                    mainTable.append(row(attr, data[attr]));
                }
            }
        });
    }

    function editKey(key) {
        /* Find the row with key in first column (key column). */
        var format = '#mainTable tbody td:first-child:contains("{key}")',
            selector = format.replace(/{key}/, key),
            cells = $(selector).parent().children(),
            key = cells[0].textContent,
            value = cells[1].textContent,
            keyInput = $('#keyInput'),
            valueInput = $('#valueInput');

        /* Load the key and value texts into inputs
         * Select value text so it can be directly typed to
         */
        keyInput.val(key);
        valueInput.val(value);
        valueInput.select();
    }

    /**
     * Delete key-value pair.
     *
     * \param key (str) The key to be deleted.
     */
    function deleteKey(key) {
        /*
         * Delete the key.
         * Reload keys and values in table to reflect the deleted ones.
         * Set keyboard focus to key input: ready to start typing.
         */
        $.post('api/delete', {key: key}, function () {
            refreshTable();
            $('#keyInput').focus();
        });
    }



