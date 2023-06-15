"use strict";

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        themeSystem: 'bootstrap5',
        headerToolbar: {
            left: "prev,next today",
            center: "title",
            right: "dayGridMonth, timeGridWeek, timeGridDay"

        },
        events: [
            {
                id: 1,
                title: "Hey",
                start: "2023-06-23T7:30:00",
                end: "2023-06-23T10:30:00",
                allDay: false
            },
        ]
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

        var newEvent = { title:title,start:startTime,end:endTime, allDay:false}

        calendar.addEvent(newEvent,calendar.events)


    })
})








