package com.globaroman.erdr_web.controller;

import com.globaroman.erdr_web.service.*;
import com.globaroman.erdr_web.view.OpenFileFromOS;
import com.globaroman.erdr_web.view.PathToFile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
@AllArgsConstructor
public class WebController {
    private final RAWService rawService;
    private final OpenFileFromOS openFileFromOS;
    private final ActionOneService oneService;
    private final ActionTwoService twoService;
    private final ActionThreeService threeService;
    private final ActionFourService fourService;
    private final ActionFiveService fiveService;
    private final ActionSixService sixService;
    private final ActionSevenService sevenService;

    @GetMapping("/main")
    public String getMainPage() {
        return "main";
    }

    @GetMapping("/oneAction")
    public String getImplOneAction() {
        oneService.compareTwoLists();
        openFileFromOS.openFile(PathToFile.RESULT);
        return "main";
    }

    @GetMapping("/twoAction")
    public String getImplTwoAction() {
        twoService.compareTwoLists();
        openFileFromOS.openFile(PathToFile.RESULT);
        return "main";
    }

    @GetMapping("/threeAction")
    public String getImplThreeAction() {
        threeService.prepareListAsShape();
        openFileFromOS.openFile(PathToFile.RESULT);
        return "main";
    }

    @GetMapping("/fourAction")
    public String getImplFourAction() {
        fourService.prepareListAsShape();
        openFileFromOS.openFile(PathToFile.RESULT);
        return "main";
    }

    @GetMapping("/fiveAction")
    public String getImplFiveAction() {
        return "pageFivePress";
    }

    @GetMapping("/fiveAction/GU")
    public String getImplFiveActionForGU() {
        fiveService.prepareListNumberToSimpleShapeForGU();
        openFileFromOS.openFile(PathToFile.RESULT);
        return "main";
    }

    @GetMapping("/fiveAction/DPD")
    public String getImplFiveActionForDPD() {
        fiveService.prepareListNumberToSimpleShapeForDPD();
        openFileFromOS.openFile(PathToFile.RESULT);
        return "main";
    }

    @GetMapping("/sixAction")
    public String getImplSixAction(@RequestParam(value = "amountRows", required = false, defaultValue = "0") int amountRows, Model model) {
        model.addAttribute("amountRows", getAmountRowsFromFile());
        return "pageSixPress";
    }

    @GetMapping("/sixAction/addRows")
    public String processAddRowsToLine(@RequestParam(value = "amountRows") int amountRows) {
        sixService.addingRows(amountRows);
        openFileFromOS.openFile(PathToFile.RESULT);
        return "main";
    }

    @GetMapping("/sevenAction")
    public String getImplSevenAction() {
        sevenService.prepareShapeForAssignment();
        openFileFromOS.openFile(PathToFile.RESULT);
        return "main";
    }

    @GetMapping("/openListOne")
    public String openListOneFromPC() {
        openFileFromOS.openFile(PathToFile.LIST_ONE);
        return "main";
    }

    @GetMapping("/openListTwo")
    public String openListTWoFromPC() {
        openFileFromOS.openFile(PathToFile.LIST_TWO);
        return "main";
    }

    @GetMapping("/openResult")
    public String openListResultFromPC() {
        openFileFromOS.openFile(PathToFile.RESULT);
        return "main";
    }

    private int getAmountRowsFromFile() {
        List<String> list = new ArrayList<>();
        if (Files.notExists(Paths.get(PathToFile.AMOUNT_ROWS))) {
            new File(PathToFile.AMOUNT_ROWS);
            list.add("5");
            rawService.writeInformationToFile(PathToFile.AMOUNT_ROWS, list);
        } else {
            list = rawService.readInformationFromFile(PathToFile.AMOUNT_ROWS);
        }
        return Integer.parseInt(list.get(0));
    }

}
