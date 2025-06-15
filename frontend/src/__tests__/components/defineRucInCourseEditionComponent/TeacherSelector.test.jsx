// TeacherSelector.test.js
import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import TeacherSelector from "../../../components/defineRucInCourseEditionComponent/TeacherSelector";

test("renders TeacherSelector and allows selection", () => {
    const teachers = [{ id: "1", name: "Prof. X", email: "x@school.edu" }];
    const handleChange = jest.fn();

    render(<TeacherSelector teachers={teachers} value="" onChange={handleChange} />);

    expect(screen.getByLabelText("Teacher")).toBeInTheDocument();

    fireEvent.change(screen.getByRole("combobox"), { target: { value: "1" } });
    expect(handleChange).toHaveBeenCalledTimes(1);
});
