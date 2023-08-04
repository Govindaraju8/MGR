CREATE OR REPLACE Procedure SpTrans(ano int,trtype char(1),amt numeric) 
    AS $$
    BEGIN
         if (trtype='D') then
  	   INSERT INTO transaction VALUES (ano,current_date,'D',amt); 	   Update Accounts set balance=balance+amt where accno=ano;
          else
	    INSERT INTO transaction VALUES (ano,current_date,'W',amt);
	 Update Accounts set balance=balance-amt where accno=ano;                  end if;
    END;
    $$LANGUAGE plpgsql;
