package com.myCompany.RepairAgency.servlet.request.get;


import com.myCompany.RepairAgency.servlet.request.IActionCommand;
import com.myCompany.RepairAgency.servlet.request.get.realization.*;

public enum GetCommandEnum{
    LOGIN {{
        this.command = new ShowLoginFormCommandI();
    }},
    SIGNUP {{
        this.command = new ShowSignupFormCommandI();
    }},
    ABOUT {{
        this.command = new ShowAboutCommandI();
    }},
    FAQS {{
        this.command = new ShowFAQsCommandI();
    }},
    HOME {{
        this.command = new ShowHomeCommandI();
    }},
    PRICING {{
        this.command = new ShowPricingCommandI();
    }},
    CABINET {{
        this.command = new ShowProfileCommandI();
    }},
    ORDER {{
        this.command = new ShowOrderPageCommandI();
    }};


    IActionCommand command;
    public IActionCommand getCurrentCommand() {
        return command;
    }
}