package com.example.springrest2.controller;

import com.example.springrest2.domain.Task;
import com.example.springrest2.dto.TaskCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks") // 접두사~
public class TaskApiController {
    private final Map<Long, Task> taskStore = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0); // 1씩 증가하는데 꼬이지 않게 하는 거라고 생각하면 된대

    // POST /api/tasks - 할 일 생성
    @PostMapping
    public Task createTask(@RequestBody TaskCreateRequest request) {
        long id = sequence.incrementAndGet(); // 1
        Task task = new Task(id, request.title(), false);
        taskStore.put(id, task);
        return task;
    }
}
