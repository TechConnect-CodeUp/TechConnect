"use strict";
import { Calendar } from '@fullcalendar/core'
import multiMonthPlugin from '@fullcalendar/multimonth'
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        // plugins: [multiMonthPlugin],
        themeSystem: 'bootstrap5',
                headerToolbar: {
                    left: "prev,next",
                    center: "title",
                    right: "dayGridMonth, timeGridWeek, timeGridDay ,list"

                },
                events: [],
                selectable: true,
                dayMaxEventRows: true, // for all non-TimeGrid views
                views: {
                   timeGrid: {
                   dayMaxEventRows: 6,

            }
        }
    })

    calendar.render();

    $(".btn").click( function () {
        console.log('clicked')
        var title = document.getElementById("exampleFormControlInput1").value
        var startTime = document.getElementById("eventstart-time").value
        var endTime = document.getElementById("eventend-time").value
        console.log(title)
        console.log(startTime)
        console.log(endTime)

        var newEvent = {}
        newEvent.title = title
        newEvent.start = startTime
        newEvent.end = endTime
        newEvent.allDay = false

        calendar.addEvent(newEvent);

        var events = calendar.getEvents();

    })
})








