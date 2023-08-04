CREATE OR REPLACE Procedure Withdraw(ano int,amt numeric) 
     AS $$
    BEGIN
      INSERT INTO transaction VALUES (ano,current_date,'W',amt);
	  Update Accounts set balance=balance-amt where accno=ano;
    END;
    $$ LANGUAGE plpgsql;
