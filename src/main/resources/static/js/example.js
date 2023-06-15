"use strict";

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        themeSystem: 'bootstrap5',
        selectable:true,
        headerToolbar: {
            left: "prev,next today",
            center: "title",
            right: "dayGridMonth, timeGridWeek, timeGridDay"

        },
        events: []
    });

    calendar.render();

    $(".btn").click( function () {
        console.log('clicked')
        var title = document.getElementById("exampleFormControlInput1").value
        var startTime = document.getElementById("eventstart-time").value
        var endTime = document.getElementById("eventend-time").value
        console.log(title)
        console.log(startTime)
        console.log(endTime)

        var newEvent = {};
        newEvent.title = title
        newEvent.start = startTime
        newEvent.end = endTime
        newEvent.allDay = false

    })
})








