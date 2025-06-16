import { render, screen } from "@testing-library/react";
import RemoveStudentPage from "../../Pages/studentPage/RemoveStudentPage";

jest.mock("../../components/NavBar", () => () => <div>NavBar</div>);
jest.mock("../../components/Footer", () => () => <div>Footer</div>);
jest.mock("../../components/studentComponent/StudentRemoval", () => () => <div>StudentRemoval</div>);

describe("RemoveStudentPage", () => {
    it("renders NavBar, StudentRemoval, and Footer", () => {
        render(<RemoveStudentPage />);
        expect(screen.getByText("NavBar")).toBeInTheDocument();
        expect(screen.getByText("StudentRemoval")).toBeInTheDocument();
        expect(screen.getByText("Footer")).toBeInTheDocument();
    });
});
