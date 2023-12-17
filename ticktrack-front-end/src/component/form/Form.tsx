import './Form.css';
import {useUser} from "../../context/UserContext.tsx";
import React, {useRef, useState} from "react";
import {useTaskDispatcher} from "../../context/TaskContext.tsx";
import {saveTask} from "../../service/task-service.tsx";
import {TaskDTO} from "../../dto/TaskDTO.ts";

export function Form() {
    const user = useUser();
    const [value, setValue] = useState("");
    const txtRef = useRef<HTMLInputElement>(null);
    const taskDispatcher = useTaskDispatcher();

    function handleSubmit(e: React.FormEvent) {
        e.preventDefault();
        if (!value.trim()) return;
        saveTask(new TaskDTO(null, value, null, user?.email!))
            .then(task => {
                taskDispatcher({type: 'add', task});
                setValue("");
                txtRef.current!.focus();
            })
            .catch(err => {
                alert("Failed to save the task, try again!");
            });
    }

    return (
        <form onSubmit={handleSubmit}
              className="Form bg-body d-flex gap-1 p-2 border-bottom">
            <input className="form-control shadow-sm rounded-0"
                   ref={txtRef}
                   onChange={e => setValue(e.target.value)}
                   value={value}
                   placeholder="Type your task here"
                   type="text"/>
            <button className="btn btn-primary shadow-sm rounded-0">
                ADD
            </button>
        </form>
    );
}