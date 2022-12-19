package com.myCompany.RepairAgency.servlet.request.get;


import com.myCompany.RepairAgency.servlet.request.ActionCommand;
import com.myCompany.RepairAgency.servlet.request.get.realization.*;

public enum GetCommandEnum{
    LOGIN {{
        this.command = new ShowLoginFormCommand();
    }},
    SIGNUP {{
        this.command = new ShowSignupFormCommand();
    }},
    ABOUT {{
        this.command = new ShowAboutCommand();
    }},
    FAQS {{
        this.command = new ShowFAQsCommand();
    }},
    HOME {{
        this.command = new ShowHomeCommand();
    }},
    PRICING {{
        this.command = new ShowPricingCommand();
    }};


    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}