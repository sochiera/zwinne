#! /usr/bin/env ruby

#USAGE: (ruby) test_jednostkowy_generator_danych_csv.rb "path/to/file.csv" columns rows

require 'csv'

$path = ARGV[0]
columns = ARGV[1].to_i
rows = ARGV[2].to_i

rowCounter = 0

result = -1

sum = 0.0
sum_deviation = 0.0
numbers = 0.0

CSV.foreach($path) do |row|

	if row.size != columns+2
		result = rowCounter
		break
	end

	if row[0].to_i != rowCounter
		result = rowCounter
		break
	end

	unless row[1].size == 25
		result = rowCounter
		break
	end

	for i in 2...(row.size) do
		sum += row[i].to_f
		numbers += 1.0
	end
	rowCounter += 1
end


CSV.foreach($path) do |row|

	for i in 2...(row.size) do
		sum_deviation += (row[i].to_f - sum/numbers)*(row[i].to_f - sum/numbers)
	end

end

Mean = 0.7
Deviation = 0.5

if result == -1

	error = 0.01
	if(numbers < 10000)
		error += 0.1
	end

	if(numbers < 100)
		error += 0.3
	end
	
	if((sum/numbers) +  Mean and (sum/numbers) - error < Mean and 
	Math.sqrt(sum_deviation/numbers) + error > Deviation and Math.sqrt(sum_deviation/numbers) - error < Deviation )
		puts "ok"
	else
		puts "failed"
		puts "Data is distributed around " + (sum/numbers).to_s + ". Standard deviation is " + (Math.sqrt(sum_deviation/numbers)).to_s + "."
	end
	
else
	puts "failed"
	puts "Data in line number " + result.to_s + " isn't correct."
end











