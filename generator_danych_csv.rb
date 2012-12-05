#! /usr/bin/env ruby

#USAGE: (ruby) generator_danych_csv.rb "path/to/file.csv" columns rows

require 'csv'

$path = ARGV[0]

Mean = 0.7
Deviation = 0.707107
Pi = 3.1415926
E = 2.7182818



CSV.open($path, "wb") do |file|

	columns = ARGV[1].to_i
	rows = ARGV[2].to_i
	
	time = Time.new

	rows.times do |c|
		row = [c.to_s]
		row << time.inspect.to_s

		columns.times do
			row << ((1.0/(Deviation*Math.sqrt(2.0*Pi)))*(E**((((rand(-10.0..10.0)-Mean)/Deviation)**2.0)/(-2.0)))).to_s
		end

		file << row
	end
end












