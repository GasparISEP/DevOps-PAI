import React from "react";
import { render, screen } from "@testing-library/react";
import DefineRucPage from "../../pages/CourseEditionPage/DefineRucPage";

jest.mock("../../components/defineRucInCourseEditionComponent/defineRucInCourseEditionForm", () => () => {
    return <div data-testid="mock-define-ruc-form">Mock DefineRucInCourseEditionForm</div>;
});

test("renders DefineRucPage and includes DefineRucInCourseEditionForm", () => {
    render(<DefineRucPage />);
    expect(screen.getByTestId("mock-define-ruc-form")).toBeInTheDocument();
});
