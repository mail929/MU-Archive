class FIBONACCI

creation make

method

   make is
      do
	 -- Compute various fibonacci numbers
	 io.put_string("fib(5) = ")
         io.put_integer(obj.fibonacci(5))
         io.put_string("%N")
	 io.put_string("fib(10) = ")
         io.put_integer(obj.fibonacci(10))
         io.put_string("%N")
	 io.put_string("fib(20) = ")
         io.put_integer(obj.fibonacci(20))
         io.put_string("%N")
      end
   fibonacci(i: INTEGER): INTEGER is
      require
         i >= 0
      do
         if i = 0 then
            Result := 1
         else
	    if i = 1 then
               Result := 1
            else
               Result := obj.fibonacci(i - 1) + obj.fibonacci(i - 2)
            end
	 end
      end

end

