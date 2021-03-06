#ifndef WORKFLOW_GREPFILE_H
#define WORKFLOW_GREPFILE_H
#include "IWorker.h"

class GrepFile : public IWorker {
private:
    std::vector<std::string> data;
    std::string word;

public:
    void setArgs (const std::list<std::string>& block_data) override;
    Text& work (Text&) override;
};

#endif //WORKFLOW_GREPFILE_H
