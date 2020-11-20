using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;

namespace FastFashion.Models
{
    public class SeedData
    {

        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new FashionItemContext(
                serviceProvider.GetRequiredService<
                    DbContextOptions<FashionItemContext>>()))
            {
                // Look for any movies.
                if (context.FashionItems.Any())
                {
                    return;   // DB has been seeded
                }


                context.FashionItems.AddRange(

                    new FashionItem {
                    
                        Type="asd",
                        Style="igen",
                        Age=5,
                        Detail="nincs",
                        pictureUri="c:/nem"

                    },

                    new FashionItem
                    {

                        Type = "sad",
                        Style = "ccc",
                        Age = 2,
                        Detail = "ddddd",
                        pictureUri = "c:/nemaaaa"

                    }


                    );


            }

        }
    }

}
